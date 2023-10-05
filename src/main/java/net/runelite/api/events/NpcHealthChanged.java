package net.runelite.api.events;

import lombok.Value;
import net.runelite.api.NPC;

/**
 * Gets sent when an Npc's health is updated
 */
@Value
public class NpcHealthChanged {
    private final NPC npc;
}
