/*
 * Copyright (c) 2018, Kamiel
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
 * An event where a menu has been opened.
 */
public class MenuOpened
{
	/**
	 * The menu entries in the newly opened menu.
	 * <p>
	 * The entries in this menu are reversed, the last entry in the
	 * array will appear first (at the top) in the opened menu.
	 */
	private MenuEntry[] menuEntries;

    public MenuOpened() {
    }

    /**
	 * Gets the entry that will be displayed first in the menu.
	 *
	 * @return the first entry
	 */
	public MenuEntry getFirstEntry()
	{
		if (menuEntries.length > 0)
		{
			return menuEntries[menuEntries.length - 1];
		}

		return null;
	}

    public MenuEntry[] getMenuEntries() {
        return this.menuEntries;
    }

    public void setMenuEntries(MenuEntry[] menuEntries) {
        this.menuEntries = menuEntries;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MenuOpened)) return false;
        final MenuOpened other = (MenuOpened) o;
        if (!other.canEqual((Object) this)) return false;
        if (!java.util.Arrays.deepEquals(this.getMenuEntries(), other.getMenuEntries())) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MenuOpened;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + java.util.Arrays.deepHashCode(this.getMenuEntries());
        return result;
    }

    public String toString() {
        return "MenuOpened(menuEntries=" + java.util.Arrays.deepToString(this.getMenuEntries()) + ")";
    }
}
