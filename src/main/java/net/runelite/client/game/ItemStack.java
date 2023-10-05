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

import net.runelite.api.coords.LocalPoint;

public final class ItemStack
{
	private final int id;
	private final int quantity;
	private final LocalPoint location;

    public ItemStack(int id, int quantity, LocalPoint location) {
        this.id = id;
        this.quantity = quantity;
        this.location = location;
    }

    public int getId() {
        return this.id;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public LocalPoint getLocation() {
        return this.location;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ItemStack)) return false;
        final ItemStack other = (ItemStack) o;
        if (this.getId() != other.getId()) return false;
        if (this.getQuantity() != other.getQuantity()) return false;
        final Object this$location = this.getLocation();
        final Object other$location = other.getLocation();
        if (this$location == null ? other$location != null : !this$location.equals(other$location)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        result = result * PRIME + this.getQuantity();
        final Object $location = this.getLocation();
        result = result * PRIME + ($location == null ? 43 : $location.hashCode());
        return result;
    }

    public String toString() {
        return "ItemStack(id=" + this.getId() + ", quantity=" + this.getQuantity() + ", location=" + this.getLocation() + ")";
    }
}
