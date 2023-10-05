package net.runelite.api.events;

import lombok.Value;
import net.runelite.api.NPC;
import net.runelite.api.NPCComposition;

/**
 * Fires after the composition of an {@link NPC} changes.
 */
@Value
public class NpcChanged
{
    /**
     * The NPC of which the composition changed.
     */
    private final NPC npc;

    /**
     * The old composition of the NPC
     */
    private final NPCComposition old;
}