package net.runelite.client.plugins.config;

import java.util.List;

public interface SearchablePlugin
{
    String getSearchableName();

    List<String> getKeywords();

    default boolean isPinned()
    {
        return false;
    }
}