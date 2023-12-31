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
package net.runelite.api.events;

import net.runelite.api.Actor;
import net.runelite.api.Player;

/**
 * An event where a {@link Player} has despawned.
 * <p>
 * Note: This event does not get called for the local player.
 */
public final class PlayerDespawned implements ActorDespawned
{
	/**
	 * The despawned player.
	 */
	private final Player player;

    public PlayerDespawned(Player player) {
        this.player = player;
    }

    @Override
	public Actor getActor()
	{
		return player;
	}

    public Player getPlayer() {
        return this.player;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PlayerDespawned)) return false;
        final PlayerDespawned other = (PlayerDespawned) o;
        final Object this$player = this.getPlayer();
        final Object other$player = other.getPlayer();
        if (this$player == null ? other$player != null : !this$player.equals(other$player)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $player = this.getPlayer();
        result = result * PRIME + ($player == null ? 43 : $player.hashCode());
        return result;
    }

    public String toString() {
        return "PlayerDespawned(player=" + this.getPlayer() + ")";
    }
}
