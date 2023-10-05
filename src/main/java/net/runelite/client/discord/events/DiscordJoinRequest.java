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
package net.runelite.client.discord.events;

/**
 * Called when another discord user wants to join the game of the logged in user
 */
public final class DiscordJoinRequest
{
	/**
	 * The userId for the user that requests to join
	 */
	private final String userId;

	/**
	 * The username of the user that requests to join
	 */
	private final String username;

	/**
	 * The discriminator of the user that requests to join
	 */
	private final String discriminator;

	/**
	 * The avatar of the user that requests to join
	 */
	private final String avatar;

    public DiscordJoinRequest(String userId, String username, String discriminator, String avatar) {
        this.userId = userId;
        this.username = username;
        this.discriminator = discriminator;
        this.avatar = avatar;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getUsername() {
        return this.username;
    }

    public String getDiscriminator() {
        return this.discriminator;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof DiscordJoinRequest)) return false;
        final DiscordJoinRequest other = (DiscordJoinRequest) o;
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId)) return false;
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
        final Object this$discriminator = this.getDiscriminator();
        final Object other$discriminator = other.getDiscriminator();
        if (this$discriminator == null ? other$discriminator != null : !this$discriminator.equals(other$discriminator))
            return false;
        final Object this$avatar = this.getAvatar();
        final Object other$avatar = other.getAvatar();
        if (this$avatar == null ? other$avatar != null : !this$avatar.equals(other$avatar)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $userId = this.getUserId();
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $discriminator = this.getDiscriminator();
        result = result * PRIME + ($discriminator == null ? 43 : $discriminator.hashCode());
        final Object $avatar = this.getAvatar();
        result = result * PRIME + ($avatar == null ? 43 : $avatar.hashCode());
        return result;
    }

    public String toString() {
        return "DiscordJoinRequest(userId=" + this.getUserId() + ", username=" + this.getUsername() + ", discriminator=" + this.getDiscriminator() + ", avatar=" + this.getAvatar() + ")";
    }
}
