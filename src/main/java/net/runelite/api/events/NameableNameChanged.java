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

import net.runelite.api.Nameable;

/**
 * An event where a {@link Nameable} has had their name changed.
 */
public final class NameableNameChanged
{
	/**
	 * The nameable that changed names.
	 */
	private final Nameable nameable;

    public NameableNameChanged(Nameable nameable) {
        this.nameable = nameable;
    }

    public Nameable getNameable() {
        return this.nameable;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof NameableNameChanged)) return false;
        final NameableNameChanged other = (NameableNameChanged) o;
        final Object this$nameable = this.getNameable();
        final Object other$nameable = other.getNameable();
        if (this$nameable == null ? other$nameable != null : !this$nameable.equals(other$nameable)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $nameable = this.getNameable();
        result = result * PRIME + ($nameable == null ? 43 : $nameable.hashCode());
        return result;
    }

    public String toString() {
        return "NameableNameChanged(nameable=" + this.getNameable() + ")";
    }
}
