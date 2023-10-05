/*
 * Copyright (c) 2018, Tomas Slusny <slusnucky@gmail.com>
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
package net.runelite.client.events;

import net.runelite.client.ui.NavigationButton;

public final class PluginToolbarButtonAdded
{
	private final NavigationButton button;
	private final int index;

    public PluginToolbarButtonAdded(NavigationButton button, int index) {
        this.button = button;
        this.index = index;
    }

    public NavigationButton getButton() {
        return this.button;
    }

    public int getIndex() {
        return this.index;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PluginToolbarButtonAdded)) return false;
        final PluginToolbarButtonAdded other = (PluginToolbarButtonAdded) o;
        final Object this$button = this.getButton();
        final Object other$button = other.getButton();
        if (this$button == null ? other$button != null : !this$button.equals(other$button)) return false;
        if (this.getIndex() != other.getIndex()) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $button = this.getButton();
        result = result * PRIME + ($button == null ? 43 : $button.hashCode());
        result = result * PRIME + this.getIndex();
        return result;
    }

    public String toString() {
        return "PluginToolbarButtonAdded(button=" + this.getButton() + ", index=" + this.getIndex() + ")";
    }
}
