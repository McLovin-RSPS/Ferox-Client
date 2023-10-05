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

import net.runelite.api.Actor;
import net.runelite.api.NPC;

/**
 * An event where an {@link NPC} has spawned.
 */
public final class NpcSpawned implements ActorSpawned
{
	/**
	 * The spawned NPC.
	 */
	private final NPC npc;

    public NpcSpawned(NPC npc) {
        this.npc = npc;
    }

    @Override
	public Actor getActor()
	{
		return npc;
	}

    public NPC getNpc() {
        return this.npc;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof NpcSpawned)) return false;
        final NpcSpawned other = (NpcSpawned) o;
        final Object this$npc = this.getNpc();
        final Object other$npc = other.getNpc();
        if (this$npc == null ? other$npc != null : !this$npc.equals(other$npc)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $npc = this.getNpc();
        result = result * PRIME + ($npc == null ? 43 : $npc.hashCode());
        return result;
    }

    public String toString() {
        return "NpcSpawned(npc=" + this.getNpc() + ")";
    }
}
