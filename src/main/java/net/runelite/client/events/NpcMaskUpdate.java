package net.runelite.client.events;

import lombok.Value;
import net.runelite.api.NPC;

/**
 * Posted when an npc goes through an update each server cycle
 */
@Value
public class NpcMaskUpdate {
    private final NPC npc;
}
