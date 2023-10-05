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
package net.runelite.client.events;

import net.runelite.client.plugins.Plugin;

public class PluginChanged
{
	private final Plugin plugin;
	private final boolean loaded;

    public PluginChanged(Plugin plugin, boolean loaded) {
        this.plugin = plugin;
        this.loaded = loaded;
    }

    public Plugin getPlugin() {
        return this.plugin;
    }

    public boolean isLoaded() {
        return this.loaded;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PluginChanged)) return false;
        final PluginChanged other = (PluginChanged) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$plugin = this.getPlugin();
        final Object other$plugin = other.getPlugin();
        if (this$plugin == null ? other$plugin != null : !this$plugin.equals(other$plugin)) return false;
        if (this.isLoaded() != other.isLoaded()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PluginChanged;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $plugin = this.getPlugin();
        result = result * PRIME + ($plugin == null ? 43 : $plugin.hashCode());
        result = result * PRIME + (this.isLoaded() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "PluginChanged(plugin=" + this.getPlugin() + ", loaded=" + this.isLoaded() + ")";
    }
}
