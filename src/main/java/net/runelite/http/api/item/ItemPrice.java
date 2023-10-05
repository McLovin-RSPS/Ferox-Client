/*
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
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
package net.runelite.http.api.item;

import java.time.Instant;

public class ItemPrice
{
	private Item item;
	private int price;
	private Instant time;

    public ItemPrice() {
    }

    public Item getItem() {
        return this.item;
    }

    public int getPrice() {
        return this.price;
    }

    public Instant getTime() {
        return this.time;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ItemPrice)) return false;
        final ItemPrice other = (ItemPrice) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$item = this.getItem();
        final Object other$item = other.getItem();
        if (this$item == null ? other$item != null : !this$item.equals(other$item)) return false;
        if (this.getPrice() != other.getPrice()) return false;
        final Object this$time = this.getTime();
        final Object other$time = other.getTime();
        if (this$time == null ? other$time != null : !this$time.equals(other$time)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ItemPrice;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $item = this.getItem();
        result = result * PRIME + ($item == null ? 43 : $item.hashCode());
        result = result * PRIME + this.getPrice();
        final Object $time = this.getTime();
        result = result * PRIME + ($time == null ? 43 : $time.hashCode());
        return result;
    }

    public String toString() {
        return "ItemPrice(item=" + this.getItem() + ", price=" + this.getPrice() + ", time=" + this.getTime() + ")";
    }
}
