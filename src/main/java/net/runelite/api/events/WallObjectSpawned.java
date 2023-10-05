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

import net.runelite.api.Tile;
import net.runelite.api.WallObject;

/**
 * An event where a {@link WallObject} is added to a {@link Tile}.
 */
public class WallObjectSpawned
{
	/**
	 * The affected tile.
	 */
	private Tile tile;
	/**
	 * The newly spawned wall object.
	 */
	private WallObject wallObject;

    public WallObjectSpawned() {
    }

    public Tile getTile() {
        return this.tile;
    }

    public WallObject getWallObject() {
        return this.wallObject;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public void setWallObject(WallObject wallObject) {
        this.wallObject = wallObject;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof WallObjectSpawned)) return false;
        final WallObjectSpawned other = (WallObjectSpawned) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$tile = this.getTile();
        final Object other$tile = other.getTile();
        if (this$tile == null ? other$tile != null : !this$tile.equals(other$tile)) return false;
        final Object this$wallObject = this.getWallObject();
        final Object other$wallObject = other.getWallObject();
        if (this$wallObject == null ? other$wallObject != null : !this$wallObject.equals(other$wallObject))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof WallObjectSpawned;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $tile = this.getTile();
        result = result * PRIME + ($tile == null ? 43 : $tile.hashCode());
        final Object $wallObject = this.getWallObject();
        result = result * PRIME + ($wallObject == null ? 43 : $wallObject.hashCode());
        return result;
    }

    public String toString() {
        return "WallObjectSpawned(tile=" + this.getTile() + ", wallObject=" + this.getWallObject() + ")";
    }
}
