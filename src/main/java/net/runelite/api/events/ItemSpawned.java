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

import net.runelite.api.Item;
import net.runelite.api.Tile;

/**
 * Called when an item pile spawns on the ground. When the client loads a new scene,
 * all item piles are implicitly reset and a new spawn event will be sent.
 */
public final class ItemSpawned
{
	private final Tile tile;
	private final Item item;

    public ItemSpawned(Tile tile, Item item) {
        this.tile = tile;
        this.item = item;
    }

    public Tile getTile() {
        return this.tile;
    }

    public Item getItem() {
        return this.item;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ItemSpawned)) return false;
        final ItemSpawned other = (ItemSpawned) o;
        final Object this$tile = this.getTile();
        final Object other$tile = other.getTile();
        if (this$tile == null ? other$tile != null : !this$tile.equals(other$tile)) return false;
        final Object this$item = this.getItem();
        final Object other$item = other.getItem();
        if (this$item == null ? other$item != null : !this$item.equals(other$item)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $tile = this.getTile();
        result = result * PRIME + ($tile == null ? 43 : $tile.hashCode());
        final Object $item = this.getItem();
        result = result * PRIME + ($item == null ? 43 : $item.hashCode());
        return result;
    }

    public String toString() {
        return "ItemSpawned(tile=" + this.getTile() + ", item=" + this.getItem() + ")";
    }
}
