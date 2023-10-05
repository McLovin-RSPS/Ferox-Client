/*
 * Copyright (c) 2018 Charlie Waters
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

/**
 * An event where the focus state of the client changes.
 * <p>
 * Examples of when this event may trigger include:
 * <ul>
 *     <li>Alt-tabbing to a different window</li>
 *     <li>Clicking outside the client window</li>
 *     <li>Clicking the client window from a different focused window</li>
 * </ul>
 */
public class FocusChanged
{
	/**
	 * The new focus state.
	 */
	private boolean focused;

    public FocusChanged() {
    }

    public boolean isFocused() {
        return this.focused;
    }

    public void setFocused(boolean focused) {
        this.focused = focused;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof FocusChanged)) return false;
        final FocusChanged other = (FocusChanged) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.isFocused() != other.isFocused()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof FocusChanged;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + (this.isFocused() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "FocusChanged(focused=" + this.isFocused() + ")";
    }
}
