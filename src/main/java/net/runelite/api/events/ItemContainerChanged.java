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
import net.runelite.api.ItemContainer;

/**
 * An event called whenever the stack size of an {@link net.runelite.api.Item}
 * in an {@link ItemContainer} is modified.
 * <p>
 * Examples of when this event may trigger include:
 * <ul>
 *     <li>Withdrawing an item from bank (triggers for both bank and player inv)
 *     <li>Picking up an item</li>
 *     <li>Dropping an item</li>
 * </ul>
 */
public final class ItemContainerChanged
{
	/**
	 * The modified item container.
	 */
	private final int widgetId;

	private final int[] items;

	private final Item[] itemContainer;

    public ItemContainerChanged(int widgetId, int[] items, Item[] itemContainer) {
        this.widgetId = widgetId;
        this.items = items;
        this.itemContainer = itemContainer;
    }

    public int getWidgetId() {
        return this.widgetId;
    }

    public int[] getItems() {
        return this.items;
    }

    public Item[] getItemContainer() {
        return this.itemContainer;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ItemContainerChanged)) return false;
        final ItemContainerChanged other = (ItemContainerChanged) o;
        if (this.getWidgetId() != other.getWidgetId()) return false;
        if (!java.util.Arrays.equals(this.getItems(), other.getItems())) return false;
        if (!java.util.Arrays.deepEquals(this.getItemContainer(), other.getItemContainer())) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getWidgetId();
        result = result * PRIME + java.util.Arrays.hashCode(this.getItems());
        result = result * PRIME + java.util.Arrays.deepHashCode(this.getItemContainer());
        return result;
    }

    public String toString() {
        return "ItemContainerChanged(widgetId=" + this.getWidgetId() + ", items=" + java.util.Arrays.toString(this.getItems()) + ", itemContainer=" + java.util.Arrays.deepToString(this.getItemContainer()) + ")";
    }
}
