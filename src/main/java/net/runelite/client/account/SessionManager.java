/*
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.account;

import com.google.gson.Gson;
import net.runelite.api.events.SessionClose;
import net.runelite.api.events.SessionOpen;
import net.runelite.client.RuneLite;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.util.LinkBrowser;
import net.runelite.http.api.account.AccountClient;
import net.runelite.http.api.account.OAuthResponse;
import net.runelite.http.api.ws.messages.LoginResponse;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.*;
import java.time.Instant;
import java.util.concurrent.ScheduledExecutorService;

import static java.lang.System.out;

@Singleton
public class SessionManager
{
	private static final File SESSION_FILE = new File(RuneLite.RUNELITE_DIR, "session");
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SessionManager.class);
    private WSClient wsclient;

	private AccountSession accountSession;

	private final EventBus eventBus;
	private ConfigManager configManager;
	private ScheduledExecutorService executor;
	private final AccountClient loginClient = new AccountClient();

	@Inject
	public SessionManager(ConfigManager configManager, EventBus eventBus, ScheduledExecutorService executor)
	{
		this.configManager = configManager;
		this.eventBus = eventBus;
		this.executor = executor;
		eventBus.register(this);
	}

	public void loadSession()
	{
		if (!SESSION_FILE.exists())
		{
            out.println("No session file exists");
			return;
		}

		AccountSession session;

		try (FileInputStream in = new FileInputStream(SESSION_FILE))
		{
			session = new Gson().fromJson(new InputStreamReader(in), AccountSession.class);

			log.debug("Loaded session for {}", session.getUsername());
		}
		catch (Exception ex)
		{
			log.warn("Unable to load session file", ex);
			return;
		}

		// Check if session is still valid
		AccountClient accountClient = new AccountClient(session.getUuid());
		if (!accountClient.sesssionCheck())
		{
			log.debug("Loaded session {} is invalid", session.getUuid());
			return;
		}

		openSession(session);
	}

	private void saveSession()
	{
		if (accountSession == null)
		{
			return;
		}

		try (FileWriter fw = new FileWriter(SESSION_FILE))
		{
			new Gson().toJson(accountSession, fw);

			log.debug("Saved session to {}", SESSION_FILE);
		}
		catch (IOException ex)
		{
			log.warn("Unable to save session file", ex);
		}
	}

	private void deleteSession()
	{
		SESSION_FILE.delete();
	}

	/**
	 * Set the given session as the active session and open a socket to the
	 * server with the given session
	 *
	 * @param session session
	 */
	private void openSession(AccountSession session)
	{
		// If the ws session already exists, don't need to do anything
		if (wsclient == null || !wsclient.checkSession(session))
		{
			if (wsclient != null)
			{
				wsclient.close();
			}

			wsclient = new WSClient(eventBus, executor);
			wsclient.connect();
		}

		accountSession = session;

		if (session.getUsername() != null)
		{
			// Initialize config for new session
			// If the session isn't logged in yet, don't switch to the new config
			configManager.switchSession(session);
		}

		eventBus.post(new SessionOpen());
	}

	private void closeSession()
	{
		if (wsclient != null)
		{
			wsclient.close();
			wsclient = null;
		}

		if (accountSession == null)
		{
			return;
		}

		log.debug("Logging out of account {}", accountSession.getUsername());

		AccountClient client = new AccountClient(accountSession.getUuid());
		try
		{
			client.logout();
		}
		catch (IOException ex)
		{
			log.warn("Unable to logout of session", ex);
		}

		accountSession = null; // No more account

		// Restore config
		configManager.switchSession(null);

		eventBus.post(new SessionClose());
	}

	public void login()
	{
		final OAuthResponse login;

		try
		{
			login = loginClient.login();
		}
		catch (IOException ex)
		{
			log.warn("Unable to get oauth url", ex);
			return;
		}

		// Create new session
		openSession(new AccountSession(login.getUid(), Instant.now()));

		// Navigate to login link
		LinkBrowser.browse(login.getOauthUrl());
	}

	@Subscribe
	public void onLogin(LoginResponse loginResponse)
	{
		log.debug("Now logged in as {}", loginResponse.getUsername());

		AccountSession session = getAccountSession();
		session.setUsername(loginResponse.getUsername());

		// Open session, again, now that we have a username
		// This triggers onSessionOpen
		openSession(session);

		// Save session to disk
		saveSession();
	}

	public void logout()
	{
		closeSession();
		deleteSession();
	}

    public AccountSession getAccountSession() {
        return this.accountSession;
    }
}
