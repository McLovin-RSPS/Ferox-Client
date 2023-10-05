/*
 * Copyright (c) 2019 Abex
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
package net.runelite.client.plugins.config;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigDescriptor;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.util.LinkBrowser;

import javax.annotation.Nullable;
import javax.swing.*;

final
class PluginConfigurationDescriptor
{
    private final String name;
    private final String description;
    private final String[] tags;

    // Can be null if its not an actual plugin (RuneLite / ChatColors)
    @Nullable
    private final Plugin plugin;

    // Can be null if it has no more configuration than the on/off toggle
    @Nullable
    private final Config config;

    @Nullable
    private final ConfigDescriptor configDescriptor;

    public PluginConfigurationDescriptor(String name, String description, String[] tags, @Nullable Plugin plugin, @Nullable Config config, @Nullable ConfigDescriptor configDescriptor) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.plugin = plugin;
        this.config = config;
        this.configDescriptor = configDescriptor;
    }

    boolean hasConfigurables()
    {
        return configDescriptor != null && !configDescriptor.getItems().stream().allMatch(item -> item.getItem().hidden());
    }

    /**
     * Creates a menu item for linking to a support page for the plugin
     *
     * @return A {@link JMenuItem} which opens the plugin's wiki page URL in the browser when clicked
     */
    @Nullable
    JMenuItem createSupportMenuItem()
    {
        JMenuItem menuItem = new JMenuItem("Wiki");
        menuItem.addActionListener(e -> LinkBrowser.browse("https://github.com/runelite/runelite/wiki/" + name.replace(' ', '-')));
        return menuItem;
    }


    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String[] getTags() {
        return this.tags;
    }

    @Nullable
    public Plugin getPlugin() {
        return this.plugin;
    }

    @Nullable
    public Config getConfig() {
        return this.config;
    }

    @Nullable
    public ConfigDescriptor getConfigDescriptor() {
        return this.configDescriptor;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PluginConfigurationDescriptor)) return false;
        final PluginConfigurationDescriptor other = (PluginConfigurationDescriptor) o;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        if (!java.util.Arrays.deepEquals(this.getTags(), other.getTags())) return false;
        final Object this$plugin = this.getPlugin();
        final Object other$plugin = other.getPlugin();
        if (this$plugin == null ? other$plugin != null : !this$plugin.equals(other$plugin)) return false;
        final Object this$config = this.getConfig();
        final Object other$config = other.getConfig();
        if (this$config == null ? other$config != null : !this$config.equals(other$config)) return false;
        final Object this$configDescriptor = this.getConfigDescriptor();
        final Object other$configDescriptor = other.getConfigDescriptor();
        if (this$configDescriptor == null ? other$configDescriptor != null : !this$configDescriptor.equals(other$configDescriptor))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        result = result * PRIME + java.util.Arrays.deepHashCode(this.getTags());
        final Object $plugin = this.getPlugin();
        result = result * PRIME + ($plugin == null ? 43 : $plugin.hashCode());
        final Object $config = this.getConfig();
        result = result * PRIME + ($config == null ? 43 : $config.hashCode());
        final Object $configDescriptor = this.getConfigDescriptor();
        result = result * PRIME + ($configDescriptor == null ? 43 : $configDescriptor.hashCode());
        return result;
    }

    public String toString() {
        return "PluginConfigurationDescriptor(name=" + this.getName() + ", description=" + this.getDescription() + ", tags=" + java.util.Arrays.deepToString(this.getTags()) + ", plugin=" + this.getPlugin() + ", config=" + this.getConfig() + ", configDescriptor=" + this.getConfigDescriptor() + ")";
    }
}