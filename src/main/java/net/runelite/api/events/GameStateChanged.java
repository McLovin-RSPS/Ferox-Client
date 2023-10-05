/*
 * Copyright (c) 2017, Steve <steve.rs.dev@gmail.com>
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

import net.runelite.api.GameState;

/**
 * An event where the clients game state has changed.
 */
public class GameStateChanged
{
	/**
	 * The new game state.
	 */
	private GameState gameState;

    public GameStateChanged() {
    }

    public GameState getGameState() {
        return this.gameState;
    }

    public GameStateChanged setGameState(GameState gameState) {
        this.gameState = gameState;
        return this;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof GameStateChanged)) return false;
        final GameStateChanged other = (GameStateChanged) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$gameState = this.getGameState();
        final Object other$gameState = other.getGameState();
        if (this$gameState == null ? other$gameState != null : !this$gameState.equals(other$gameState)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof GameStateChanged;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $gameState = this.getGameState();
        result = result * PRIME + ($gameState == null ? 43 : $gameState.hashCode());
        return result;
    }

    public String toString() {
        return "GameStateChanged(gameState=" + this.getGameState() + ")";
    }
}
