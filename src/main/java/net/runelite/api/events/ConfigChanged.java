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

/**
 * An event where a configuration entry has been modified.
 */
public class ConfigChanged
{
	/**
	 * The parent group for the key.
	 * <p>
	 * Typically set to the name of a plugin to prevent potential collisions
	 * between other key values that may have the same name.
	 */
	private String group;
	/**
	 * The configuration key that has been modified.
	 */
	private String key;
	/**
	 * The previous value of the entry.
	 */
	private String oldValue;
	/**
	 * The new value of the entry, null if the entry has been unset.
	 */
	private String newValue;

    public ConfigChanged() {
    }

    public String getGroup() {
        return this.group;
    }

    public String getKey() {
        return this.key;
    }

    public String getOldValue() {
        return this.oldValue;
    }

    public String getNewValue() {
        return this.newValue;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ConfigChanged)) return false;
        final ConfigChanged other = (ConfigChanged) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$group = this.getGroup();
        final Object other$group = other.getGroup();
        if (this$group == null ? other$group != null : !this$group.equals(other$group)) return false;
        final Object this$key = this.getKey();
        final Object other$key = other.getKey();
        if (this$key == null ? other$key != null : !this$key.equals(other$key)) return false;
        final Object this$oldValue = this.getOldValue();
        final Object other$oldValue = other.getOldValue();
        if (this$oldValue == null ? other$oldValue != null : !this$oldValue.equals(other$oldValue)) return false;
        final Object this$newValue = this.getNewValue();
        final Object other$newValue = other.getNewValue();
        if (this$newValue == null ? other$newValue != null : !this$newValue.equals(other$newValue)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ConfigChanged;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $group = this.getGroup();
        result = result * PRIME + ($group == null ? 43 : $group.hashCode());
        final Object $key = this.getKey();
        result = result * PRIME + ($key == null ? 43 : $key.hashCode());
        final Object $oldValue = this.getOldValue();
        result = result * PRIME + ($oldValue == null ? 43 : $oldValue.hashCode());
        final Object $newValue = this.getNewValue();
        result = result * PRIME + ($newValue == null ? 43 : $newValue.hashCode());
        return result;
    }

    public String toString() {
        return "ConfigChanged(group=" + this.getGroup() + ", key=" + this.getKey() + ", oldValue=" + this.getOldValue() + ", newValue=" + this.getNewValue() + ")";
    }
}
