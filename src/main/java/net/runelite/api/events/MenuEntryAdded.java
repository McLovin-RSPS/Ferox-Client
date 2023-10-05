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
package net.runelite.api.events;

import net.runelite.api.MenuEntry;

/**
 * An event when a new entry is added to a right-click menu.
 */
public class MenuEntryAdded
{
	/**
	 * The menu entry being added
	 */
	private final MenuEntry entry;




	private final int entryIndex;

    public MenuEntryAdded(MenuEntry entry, int entryIndex) {
        this.entry = entry;
        this.entryIndex = entryIndex;
    }

    public MenuEntry getEntry() {
        return this.entry;
    }

    public int getEntryIndex() {
        return this.entryIndex;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MenuEntryAdded)) return false;
        final MenuEntryAdded other = (MenuEntryAdded) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$entry = this.getEntry();
        final Object other$entry = other.getEntry();
        if (this$entry == null ? other$entry != null : !this$entry.equals(other$entry)) return false;
        if (this.getEntryIndex() != other.getEntryIndex()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MenuEntryAdded;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $entry = this.getEntry();
        result = result * PRIME + ($entry == null ? 43 : $entry.hashCode());
        result = result * PRIME + this.getEntryIndex();
        return result;
    }

    public String toString() {
        return "MenuEntryAdded(entry=" + this.getEntry() + ", entryIndex=" + this.getEntryIndex() + ")";
    }
}
