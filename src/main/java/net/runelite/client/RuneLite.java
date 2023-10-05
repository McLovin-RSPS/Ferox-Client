/*
 * Copyright (c) 2016-2017, Adam <Adam@sigterm.info>
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
package net.runelite.client;

import com.google.common.annotations.VisibleForTesting;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.sentry.Sentry;
import joptsimple.ArgumentAcceptingOptionSpec;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.util.EnumConverter;
import net.runelite.api.Client;
import net.runelite.client.account.SessionManager;
import net.runelite.client.callback.Hooks;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.CommandManager;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.discord.DiscordService;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.game.ClanManager;
import net.runelite.client.game.ItemManager;
import net.runelite.client.game.LootManager;
import net.runelite.client.menus.MenuManager;
import net.runelite.client.plugins.PluginManager;
import net.runelite.client.rs.ClientUpdateCheckMode;
import net.runelite.client.ui.ClientUI;
import net.runelite.client.ui.DrawManager;
import net.runelite.client.ui.FatalErrorDialog;
import net.runelite.client.ui.SplashScreen;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.OverlayRenderer;
import net.runelite.client.ui.overlay.WidgetOverlay;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import net.runelite.client.ui.overlay.infobox.InfoBoxOverlay;
import net.runelite.client.ui.overlay.tooltip.TooltipOverlay;
import net.runelite.client.ui.overlay.worldmap.WorldMapOverlay;
import org.apache.commons.io.FileUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.lang.System.out;

@Singleton
public class RuneLite
{
	public static final File RUNELITE_DIR = new File(System.getProperty("user.home"), ".ancestral-runelite-data");
	public static final File PROFILES_DIR = new File(RUNELITE_DIR, "profiles");
	public static final File SCREENSHOT_DIR = new File(RUNELITE_DIR, "screenshots");
	private static final File LOGS_DIR = new File(RUNELITE_DIR, "logs");
	private static final File LOGS_FILE_NAME = new File(LOGS_DIR, "application");
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(RuneLite.class);
	public static boolean devMode = false;

	private static Injector injector;

	@Inject
	public PluginManager pluginManager;

	public RuneLite() {

	}

	@Inject
	private MenuManager menuManager;

	@Inject
	private Provider<Hooks> hooks;
	@Inject
	private Client client;
	@Inject
	private EventBus eventBus;

	@Inject
	private ConfigManager configManager;

	@Inject
	private ChatMessageManager chatMessageManager;

	@Inject
	private CommandManager commandManager;

	@Inject
	private OverlayRenderer overlayRenderer;

	@Inject
	private DrawManager drawManager;

	@Inject
	private SessionManager sessionManager;

	@Inject
	private DiscordService discordService;

	@Inject
	private ClientSessionManager clientSessionManager;

	@Inject
	private ClientUI clientUI;

	@Inject
	private Provider<ItemManager> itemManager;

	@Inject
	private ClanManager clanManager;

	@Inject
	private Provider<LootManager> lootManager;
	@Inject
	private InfoBoxManager infoBoxManager;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private InfoBoxOverlay infoBoxOverlay;

	@Inject
	private TooltipOverlay tooltipOverlay;

	@Inject
	private WorldMapOverlay worldMapOverlay;


	public static void unzip(InputStream is, Path targetDir) throws IOException {
		targetDir = targetDir.toAbsolutePath();
		try (ZipInputStream zipIn = new ZipInputStream(is)) {
			for (ZipEntry ze; (ze = zipIn.getNextEntry()) != null; ) {
				Path resolvedPath = targetDir.resolve(ze.getName()).normalize();
				if (!resolvedPath.startsWith(targetDir)) {
					throw new RuntimeException("Entry with an illegal path: " + ze.getName());
				}
				if (ze.isDirectory()) {
					Files.createDirectories(resolvedPath);
				} else {
					Files.createDirectories(resolvedPath.getParent());
					Files.copy(zipIn, resolvedPath);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception
	{


		out.println("Starting RuneLite with JRE [" + Runtime.class.getPackage().getImplementationVersion() + "]");
		File itemSpriteDir = new File(RUNELITE_DIR  + "/items/sprites/");
		if(!itemSpriteDir.exists())
			itemSpriteDir.mkdirs();
		Locale.setDefault(Locale.ENGLISH);
		final OptionParser parser = new OptionParser();
		parser.accepts("developer-mode", "Enable developer tools");
		parser.accepts("debug", "Show extra debugging output");
		parser.accepts("clear-cache", "Delete game cache");
		parser.accepts("clear-data", "Delete runelite data");

		final ArgumentAcceptingOptionSpec<ClientUpdateCheckMode> updateMode = parser
			.accepts("rs", "Select client type")
			.withRequiredArg()
			.ofType(ClientUpdateCheckMode.class)
			.defaultsTo(ClientUpdateCheckMode.AUTO)
			.withValuesConvertedBy(new EnumConverter<ClientUpdateCheckMode>(ClientUpdateCheckMode.class)
			{
				@Override
				public ClientUpdateCheckMode convert(String v)
				{
					return super.convert(v.toUpperCase());
				}
			});

		parser.accepts("help", "Show this text").forHelp();
		OptionSet options = parser.parse(args);

		if (options.has("help"))
		{
			parser.printHelpOn(System.out);
			System.exit(0);
		}
		if(options.has("clear-data")) {
			FileUtils.forceDelete(RUNELITE_DIR);
		}

		final boolean developerMode = options.has("developer-mode");

		devMode = developerMode;

		PROFILES_DIR.mkdirs();

		// Setup logger
		MDC.put("logFileName", LOGS_FILE_NAME.getAbsolutePath());



		Thread.setDefaultUncaughtExceptionHandler((thread, throwable) ->
		{
			log.error("Uncaught exception:", throwable);
			if (throwable instanceof AbstractMethodError)
			{
				out.println("Classes are out of date; Build with maven again.");
			}
		});
		SplashScreen.init();
		SplashScreen.stage(0, "Retrieving client", "");
		try {
			injector = Guice.createInjector(new RuneLiteModule(
					options.valueOf(updateMode),
					developerMode, false));

			injector.getInstance(RuneLite.class).start();
		} catch (Exception e) {
			log.error("Failure during startup", e);
			SwingUtilities.invokeLater(() ->
					new FatalErrorDialog("RuneLite has encountered an unexpected error during startup.")
							.open());
		} finally {
			SplashScreen.stop();
		}
	}

	public static Injector getInjector() {
		return RuneLite.injector;
	}

	public void start() throws Exception
	{
		// Load RuneLite or Vanilla client
		final boolean isOutdated = client == null;

		if (!isOutdated)
		{
			// Inject members into client
			injector.injectMembers(client);
			out.println("Injected Client");
		}

		SplashScreen.stage(.57, null, "Loading configuration");
		// Load user configuration
		configManager.load();

		// Tell the plugin manager if client is outdated or not
		pluginManager.setOutdated(false);

		SplashScreen.stage(.70, null, "Finalizing configuration");
		// Load the plugins, but does not start them yet.
		// This will initialize configuration
		pluginManager.loadCorePlugins();

		// Plugins have provided their config, so set default config
		// to main settings
		pluginManager.loadDefaultPluginConfiguration(null);

		// Start client session
		//clientSessionManager.start();

		// Load the session, including saved configuration
		//sessionManager.loadSession();

		SplashScreen.stage(.75, null, "Starting core interface");
		// Initialize UI
		clientUI.open(this);

		// Initialize Discord service
		discordService.init();

		// Register event listeners
		eventBus.register(clientUI);
		eventBus.register(pluginManager);
		eventBus.register(overlayRenderer);
		eventBus.register(overlayManager);
		eventBus.register(drawManager);
		eventBus.register(menuManager);
		eventBus.register(chatMessageManager);
		eventBus.register(commandManager);
		eventBus.register(clanManager);
		eventBus.register(infoBoxManager);
		eventBus.register(hooks.get());

		lootManager.get();
			eventBus.register(itemManager.get());
			WidgetOverlay.createOverlays(client).forEach(overlayManager::add);

		// Add core overlays after configuration has been loaded so their properties will be
		// loaded properly
		overlayManager.add(infoBoxOverlay);
		overlayManager.add(worldMapOverlay);
		overlayManager.add(tooltipOverlay);

		// Start plugins
		pluginManager.startPlugins();

		SplashScreen.stop();

		clientUI.show();
	}

	public void shutdown()
	{
		//clientSessionManager.shutdown();
		discordService.close();
	}

	@VisibleForTesting
	public static void setInjector(Injector injector)
	{
		RuneLite.injector = injector;
	}
	private static RuneLite instance;
}
