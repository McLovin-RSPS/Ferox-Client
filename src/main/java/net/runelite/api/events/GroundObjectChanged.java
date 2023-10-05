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

import net.runelite.api.GroundObject;
import net.runelite.api.Tile;

/**
 * An event where the {@link GroundObject} on a {@link Tile} has been changed.
 */
public class GroundObjectChanged
{
	/**
	 * The affected tile.
	 */
	private Tile tile;
	/**
	 * The ground object that has been replaced.
	 */
	private GroundObject previous;
	/**
	 * The new ground object on the tile.
	 */
	private GroundObject groundObject;

    public GroundObjectChanged() {
    }

    public Tile getTile() {
        return this.tile;
    }

    public GroundObject getPrevious() {
        return this.previous;
    }

    public GroundObject getGroundObject() {
        return this.groundObject;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public void setPrevious(GroundObject previous) {
        this.previous = previous;
    }

    public void setGroundObject(GroundObject groundObject) {
        this.groundObject = groundObject;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof GroundObjectChanged)) return false;
        final GroundObjectChanged other = (GroundObjectChanged) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$tile = this.getTile();
        final Object other$tile = other.getTile();
        if (this$tile == null ? other$tile != null : !this$tile.equals(other$tile)) return false;
        final Object this$previous = this.getPrevious();
        final Object other$previous = other.getPrevious();
        if (this$previous == null ? other$previous != null : !this$previous.equals(other$previous)) return false;
        final Object this$groundObject = this.getGroundObject();
        final Object other$groundObject = other.getGroundObject();
        if (this$groundObject == null ? other$groundObject != null : !this$groundObject.equals(other$groundObject))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof GroundObjectChanged;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $tile = this.getTile();
        result = result * PRIME + ($tile == null ? 43 : $tile.hashCode());
        final Object $previous = this.getPrevious();
        result = result * PRIME + ($previous == null ? 43 : $previous.hashCode());
        final Object $groundObject = this.getGroundObject();
        result = result * PRIME + ($groundObject == null ? 43 : $groundObject.hashCode());
        return result;
    }

    public String toString() {
        return "GroundObjectChanged(tile=" + this.getTile() + ", previous=" + this.getPrevious() + ", groundObject=" + this.getGroundObject() + ")";
    }
}
