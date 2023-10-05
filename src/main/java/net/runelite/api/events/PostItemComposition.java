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

import net.runelite.api.ItemComposition;

/**
 * An event called after a new {@link ItemComposition} is created and
 * its data is initialized.
 */
public class PostItemComposition
{
	/**
	 * The newly created item.
	 */
	private ItemComposition itemComposition;

    public PostItemComposition() {
    }

    public ItemComposition getItemComposition() {
        return this.itemComposition;
    }

    public void setItemComposition(ItemComposition itemComposition) {
        this.itemComposition = itemComposition;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PostItemComposition)) return false;
        final PostItemComposition other = (PostItemComposition) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$itemComposition = this.getItemComposition();
        final Object other$itemComposition = other.getItemComposition();
        if (this$itemComposition == null ? other$itemComposition != null : !this$itemComposition.equals(other$itemComposition))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PostItemComposition;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $itemComposition = this.getItemComposition();
        result = result * PRIME + ($itemComposition == null ? 43 : $itemComposition.hashCode());
        return result;
    }

    public String toString() {
        return "PostItemComposition(itemComposition=" + this.getItemComposition() + ")";
    }
}
