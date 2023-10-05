/*
 * Copyright (c) 2017, Tomas Slusny <slusnucky@gmail.com>
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
package net.runelite.client.chat;

import java.awt.*;

public class ChatColor
{
	private ChatColorType type;
	private Color color;
	private boolean transparent;
	private boolean isDefault;

	public ChatColor(ChatColorType type, Color color, boolean transparent)
	{
		this(type, color, transparent, false);
	}

	public ChatColor(ChatColorType type, Color color, boolean transparent, boolean isDefault)
	{
		this.type = type;
		this.color = color;
		this.transparent = transparent;
		this.isDefault = isDefault;
	}

    public ChatColorType getType() {
        return this.type;
    }

    public Color getColor() {
        return this.color;
    }

    public boolean isTransparent() {
        return this.transparent;
    }

    public boolean isDefault() {
        return this.isDefault;
    }

    public void setType(ChatColorType type) {
        this.type = type;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setTransparent(boolean transparent) {
        this.transparent = transparent;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String toString() {
        return "ChatColor(type=" + this.getType() + ", color=" + this.getColor() + ", transparent=" + this.isTransparent() + ", isDefault=" + this.isDefault() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ChatColor)) return false;
        final ChatColor other = (ChatColor) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        if (this.isTransparent() != other.isTransparent()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ChatColor;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        result = result * PRIME + (this.isTransparent() ? 79 : 97);
        return result;
    }
}
