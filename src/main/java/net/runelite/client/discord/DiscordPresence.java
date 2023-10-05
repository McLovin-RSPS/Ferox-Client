/*
 * Copyright (c) 2018, Tomas Slusny <slusnucky@gmail.com>
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
package net.runelite.client.discord;

import org.slf4j.Logger;

import java.time.Instant;

import static java.lang.System.out;

/**
 * Represents Discord Rich Presence RPC data
 */
public final class DiscordPresence
{
	/**
	 * The user's current party status.
	 * Example: "Looking to Play", "Playing Solo", "In a Group"
	 *
	 * <b>Maximum: 128 characters</b>
	 */
	private final String state;

	/**
	 * What the player is currently doing.
	 * Example: "Competitive - Captain's Mode", "In Queue", "Unranked PvP"
	 *
	 * <b>Maximum: 128 characters</b>
	 */
	private final String details;

	/**
	 * Unix timestamp (seconds) for the start of the game.
	 */
	private final Instant startTimestamp;

	/**
	 * Unix timestamp (seconds) for the start of the game.
	 */
	private final Instant endTimestamp;

	/**
	 * Name of the uploaded image for the large profile artwork.
	 * Example: "default"
	 *
	 * <b>Maximum: 32 characters</b>
	 */
	private final String largeImageKey;

	/**
	 * Tooltip for the largeImageKey.
	 * Example: "Blade's Edge Arena", "Numbani", "Danger Zone"
	 *
	 * <b>Maximum: 128 characters</b>
	 */
	private final String largeImageText;

	/**
	 * Name of the uploaded image for the small profile artwork.
	 * Example: "rogue"
	 *
	 * <b>Maximum: 32 characters</b>
	 */
	private final String smallImageKey;

	/**
	 * Tooltip for the smallImageKey.
	 * Example: "Rogue - Level 100"
	 *
	 * <b>Maximum: 128 characters</b>
	 */
	private final String smallImageText;

	/**
	 * ID of the player's party, lobby, or group.
	 * Example: "ae488379-351d-4a4f-ad32-2b9b01c91657"
	 *
	 * <b>Maximum: 128 characters</b>
	 */
	private final String partyId;

	/**
	 * Current size of the player's party, lobby, or group.
	 * Example: 1
	 */
	private final int partySize;

	/**
	 * Maximum size of the player's party, lobby, or group.
	 * Example: 5
	 */
	private final int partyMax;

	/**
	 * Unique hashed string for Spectate and Join.
	 * Required to enable match interactive buttons in the user's presence.
	 * Example: "MmhuZToxMjMxMjM6cWl3amR3MWlqZA=="
	 *
	 * <b>Maximum: 128 characters</b>
	 */
	private final String matchSecret;

	/**
	 * Unique hashed string for Spectate button.
	 * This will enable the "Spectate" button on the user's presence if whitelisted.
	 * Example: "MTIzNDV8MTIzNDV8MTMyNDU0"
	 *
	 * <b>Maximum: 128 characters</b>
	 */
	private final String joinSecret;

	/**
	 * Unique hashed string for chat invitations and Ask to Join.
	 * This will enable the "Ask to Join" button on the user's presence if whitelisted.
	 * Example: "MTI4NzM0OjFpMmhuZToxMjMxMjM="
	 *
	 * <b>Maximum: 128 characters</b>
	 */
	private final String spectateSecret;

	/**
	 * Marks the matchSecret as a game session with a specific beginning and end.
	 */
	private final boolean instance;

    DiscordPresence(String state, String details, Instant startTimestamp, Instant endTimestamp, String largeImageKey, String largeImageText, String smallImageKey, String smallImageText, String partyId, int partySize, int partyMax, String matchSecret, String joinSecret, String spectateSecret, boolean instance) {
        this.state = state;
        this.details = details;
        this.startTimestamp = startTimestamp;
        this.endTimestamp = endTimestamp;
        this.largeImageKey = largeImageKey;
        this.largeImageText = largeImageText;
        System.out.println("Using large image key " + largeImageKey + " - text " + largeImageText);
        this.smallImageKey = smallImageKey;
        this.smallImageText = smallImageText;
        this.partyId = partyId;
        this.partySize = partySize;
        this.partyMax = partyMax;
        this.matchSecret = matchSecret;
        this.joinSecret = joinSecret;
        this.spectateSecret = spectateSecret;
        this.instance = instance;
    }

    public static DiscordPresenceBuilder builder() {
        return new DiscordPresenceBuilder();
    }

    public String getState() {
        return this.state;
    }

    public String getDetails() {
        return this.details;
    }

    public Instant getStartTimestamp() {
        return this.startTimestamp;
    }

    public Instant getEndTimestamp() {
        return this.endTimestamp;
    }

    public String getLargeImageKey() {
        return this.largeImageKey;
    }

    public String getLargeImageText() {
        return this.largeImageText;
    }

    public String getSmallImageKey() {
        return this.smallImageKey;
    }

    public String getSmallImageText() {
        return this.smallImageText;
    }

    public String getPartyId() {
        return this.partyId;
    }

    public int getPartySize() {
        return this.partySize;
    }

    public int getPartyMax() {
        return this.partyMax;
    }

    public String getMatchSecret() {
        return this.matchSecret;
    }

    public String getJoinSecret() {
        return this.joinSecret;
    }

    public String getSpectateSecret() {
        return this.spectateSecret;
    }

    public boolean isInstance() {
        return this.instance;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof DiscordPresence)) return false;
        final DiscordPresence other = (DiscordPresence) o;
        final Object this$state = this.getState();
        final Object other$state = other.getState();
        if (this$state == null ? other$state != null : !this$state.equals(other$state)) return false;
        final Object this$details = this.getDetails();
        final Object other$details = other.getDetails();
        if (this$details == null ? other$details != null : !this$details.equals(other$details)) return false;
        final Object this$startTimestamp = this.getStartTimestamp();
        final Object other$startTimestamp = other.getStartTimestamp();
        if (this$startTimestamp == null ? other$startTimestamp != null : !this$startTimestamp.equals(other$startTimestamp))
            return false;
        final Object this$endTimestamp = this.getEndTimestamp();
        final Object other$endTimestamp = other.getEndTimestamp();
        if (this$endTimestamp == null ? other$endTimestamp != null : !this$endTimestamp.equals(other$endTimestamp))
            return false;
        final Object this$largeImageKey = this.getLargeImageKey();
        final Object other$largeImageKey = other.getLargeImageKey();
        if (this$largeImageKey == null ? other$largeImageKey != null : !this$largeImageKey.equals(other$largeImageKey))
            return false;
        final Object this$largeImageText = this.getLargeImageText();
        final Object other$largeImageText = other.getLargeImageText();
        if (this$largeImageText == null ? other$largeImageText != null : !this$largeImageText.equals(other$largeImageText))
            return false;
        final Object this$smallImageKey = this.getSmallImageKey();
        final Object other$smallImageKey = other.getSmallImageKey();
        if (this$smallImageKey == null ? other$smallImageKey != null : !this$smallImageKey.equals(other$smallImageKey))
            return false;
        final Object this$smallImageText = this.getSmallImageText();
        final Object other$smallImageText = other.getSmallImageText();
        if (this$smallImageText == null ? other$smallImageText != null : !this$smallImageText.equals(other$smallImageText))
            return false;
        final Object this$partyId = this.getPartyId();
        final Object other$partyId = other.getPartyId();
        if (this$partyId == null ? other$partyId != null : !this$partyId.equals(other$partyId)) return false;
        if (this.getPartySize() != other.getPartySize()) return false;
        if (this.getPartyMax() != other.getPartyMax()) return false;
        final Object this$matchSecret = this.getMatchSecret();
        final Object other$matchSecret = other.getMatchSecret();
        if (this$matchSecret == null ? other$matchSecret != null : !this$matchSecret.equals(other$matchSecret))
            return false;
        final Object this$joinSecret = this.getJoinSecret();
        final Object other$joinSecret = other.getJoinSecret();
        if (this$joinSecret == null ? other$joinSecret != null : !this$joinSecret.equals(other$joinSecret))
            return false;
        final Object this$spectateSecret = this.getSpectateSecret();
        final Object other$spectateSecret = other.getSpectateSecret();
        if (this$spectateSecret == null ? other$spectateSecret != null : !this$spectateSecret.equals(other$spectateSecret))
            return false;
        if (this.isInstance() != other.isInstance()) return false;
        return true;
    }

    public String toString() {
        return "DiscordPresence(state=" + this.getState() + ", details=" + this.getDetails() + ", startTimestamp=" + this.getStartTimestamp() + ", endTimestamp=" + this.getEndTimestamp() + ", largeImageKey=" + this.getLargeImageKey() + ", largeImageText=" + this.getLargeImageText() + ", smallImageKey=" + this.getSmallImageKey() + ", smallImageText=" + this.getSmallImageText() + ", partyId=" + this.getPartyId() + ", partySize=" + this.getPartySize() + ", partyMax=" + this.getPartyMax() + ", matchSecret=" + this.getMatchSecret() + ", joinSecret=" + this.getJoinSecret() + ", spectateSecret=" + this.getSpectateSecret() + ", instance=" + this.isInstance() + ")";
    }

    public DiscordPresenceBuilder toBuilder() {
        return new DiscordPresenceBuilder().state(this.state).details(this.details).startTimestamp(this.startTimestamp).endTimestamp(this.endTimestamp).largeImageKey(this.largeImageKey).largeImageText(this.largeImageText).smallImageKey(this.smallImageKey).smallImageText(this.smallImageText).partyId(this.partyId).partySize(this.partySize).partyMax(this.partyMax).matchSecret(this.matchSecret).joinSecret(this.joinSecret).spectateSecret(this.spectateSecret).instance(this.instance);
    }
    public static class DiscordPresenceBuilder {
        private static final Logger log = org.slf4j.LoggerFactory.getLogger(DiscordPresenceBuilder.class);
        private String state;
        private String details;
        private Instant startTimestamp;
        private Instant endTimestamp;
        private String largeImageKey;
        private String largeImageText;
        private String smallImageKey;
        private String smallImageText;
        private String partyId;
        private int partySize;
        private int partyMax;
        private String matchSecret;
        private String joinSecret;
        private String spectateSecret;
        private boolean instance;

        DiscordPresenceBuilder() {
        }

        public DiscordPresenceBuilder state(String state) {
            this.state = state;
            return this;
        }

        public DiscordPresenceBuilder details(String details) {
            this.details = details;
            return this;
        }

        public DiscordPresenceBuilder startTimestamp(Instant startTimestamp) {
            this.startTimestamp = startTimestamp;
            return this;
        }

        public DiscordPresenceBuilder endTimestamp(Instant endTimestamp) {
            this.endTimestamp = endTimestamp;
            return this;
        }

        public DiscordPresenceBuilder largeImageKey(String largeImageKey) {
            this.largeImageKey = largeImageKey;
            return this;
        }

        public DiscordPresenceBuilder largeImageText(String largeImageText) {
            this.largeImageText = largeImageText;
            return this;
        }

        public DiscordPresenceBuilder smallImageKey(String smallImageKey) {
            out.println("Attempting to use rich presence key [" + smallImageKey + "]");
            this.smallImageKey = smallImageKey;
            return this;
        }

        public DiscordPresenceBuilder smallImageText(String smallImageText) {
            this.smallImageText = smallImageText;
            return this;
        }

        public DiscordPresenceBuilder partyId(String partyId) {
            this.partyId = partyId;
            return this;
        }

        public DiscordPresenceBuilder partySize(int partySize) {
            this.partySize = partySize;
            return this;
        }

        public DiscordPresenceBuilder partyMax(int partyMax) {
            this.partyMax = partyMax;
            return this;
        }

        public DiscordPresenceBuilder matchSecret(String matchSecret) {
            this.matchSecret = matchSecret;
            return this;
        }

        public DiscordPresenceBuilder joinSecret(String joinSecret) {
            this.joinSecret = joinSecret;
            return this;
        }

        public DiscordPresenceBuilder spectateSecret(String spectateSecret) {
            this.spectateSecret = spectateSecret;
            return this;
        }

        public DiscordPresenceBuilder instance(boolean instance) {
            this.instance = instance;
            return this;
        }

        public DiscordPresence build() {
            return new DiscordPresence(state, details, startTimestamp, endTimestamp, largeImageKey, largeImageText, smallImageKey, smallImageText, partyId, partySize, partyMax, matchSecret, joinSecret, spectateSecret, instance);
        }

        public String toString() {
            return "DiscordPresence.DiscordPresenceBuilder(state=" + this.state + ", details=" + this.details + ", startTimestamp=" + this.startTimestamp + ", endTimestamp=" + this.endTimestamp + ", largeImageKey=" + this.largeImageKey + ", largeImageText=" + this.largeImageText + ", smallImageKey=" + this.smallImageKey + ", smallImageText=" + this.smallImageText + ", partyId=" + this.partyId + ", partySize=" + this.partySize + ", partyMax=" + this.partyMax + ", matchSecret=" + this.matchSecret + ", joinSecret=" + this.joinSecret + ", spectateSecret=" + this.spectateSecret + ", instance=" + this.instance + ")";
        }
    }
}
