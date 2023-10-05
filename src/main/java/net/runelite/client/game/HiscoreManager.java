/*
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
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
package net.runelite.client.game;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;
import net.runelite.api.Client;
import net.runelite.client.callback.ClientThread;
import net.runelite.http.api.hiscore.HiscoreClient;
import net.runelite.http.api.hiscore.HiscoreEndpoint;
import net.runelite.http.api.hiscore.HiscoreResult;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Singleton
public class HiscoreManager
{
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(HiscoreManager.class);

	static class HiscoreKey
	{
		String username;
		HiscoreEndpoint type;

		public HiscoreKey(String username, HiscoreEndpoint type) {
			this.username = username;
			this.type = type;
		}

		public String getUsername() {
			return this.username;
		}

		public HiscoreEndpoint getType() {
			return this.type;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public void setType(HiscoreEndpoint type) {
			this.type = type;
		}

		public boolean equals(final Object o) {
			if (o == this) return true;
			if (!(o instanceof HiscoreKey)) return false;
			final HiscoreKey other = (HiscoreKey) o;
			if (!other.canEqual((Object) this)) return false;
			final Object this$username = this.getUsername();
			final Object other$username = other.getUsername();
			if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
			final Object this$type = this.getType();
			final Object other$type = other.getType();
			if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
			return true;
		}

		protected boolean canEqual(final Object other) {
			return other instanceof HiscoreKey;
		}

		public int hashCode() {
			final int PRIME = 59;
			int result = 1;
			final Object $username = this.getUsername();
			result = result * PRIME + ($username == null ? 43 : $username.hashCode());
			final Object $type = this.getType();
			result = result * PRIME + ($type == null ? 43 : $type.hashCode());
			return result;
		}

		public String toString() {
			return "HiscoreManager.HiscoreKey(username=" + this.getUsername() + ", type=" + this.getType() + ")";
		}
	}

	static final HiscoreResult EMPTY = new HiscoreResult();
	static final HiscoreResult NONE = new HiscoreResult();

	private final HiscoreClient hiscoreClient = new HiscoreClient();
	private final LoadingCache<HiscoreKey, HiscoreResult> hiscoreCache;

	@Inject
	public HiscoreManager(Client client, ScheduledExecutorService executor, ClientThread clientThread)
	{
		hiscoreCache = CacheBuilder.newBuilder()
			.maximumSize(128L)
			.expireAfterWrite(1, TimeUnit.HOURS)
			.build(new HiscoreLoader(executor, hiscoreClient));
	}

	/**
	 * Synchronously look up a players hiscore from a specified endpoint
	 *
	 * @param username Players username
	 * @param endpoint Hiscore endpoint
	 * @return HiscoreResult or null
	 * @throws IOException Upon error in fetching hiscore
	 */
	public HiscoreResult lookup(String username, HiscoreEndpoint endpoint) throws IOException
	{
		HiscoreKey hiscoreKey = new HiscoreKey(username, endpoint);
		HiscoreResult hiscoreResult = hiscoreCache.getIfPresent(hiscoreKey);
		if (hiscoreResult != null && hiscoreResult != EMPTY)
		{
			return hiscoreResult == NONE ? null : hiscoreResult;
		}

		hiscoreResult = hiscoreClient.lookup(username, endpoint);
		if (hiscoreResult == null)
		{
			hiscoreCache.put(hiscoreKey, NONE);
			return null;
		}

		hiscoreCache.put(hiscoreKey, hiscoreResult);
		return hiscoreResult;
	}

	/**
	 * Asynchronously look up a players hiscore from a specified endpoint
	 *
	 * @param username Players username
	 * @param endpoint Hiscore endpoint
	 * @return HiscoreResult or null
	 */
	public HiscoreResult lookupAsync(String username, HiscoreEndpoint endpoint)
	{
		HiscoreKey hiscoreKey = new HiscoreKey(username, endpoint);
		HiscoreResult hiscoreResult = hiscoreCache.getIfPresent(hiscoreKey);
		if (hiscoreResult != null && hiscoreResult != EMPTY)
		{
			return hiscoreResult == NONE ? null : hiscoreResult;
		}

		hiscoreCache.refresh(hiscoreKey);
		return null;
	}
}
