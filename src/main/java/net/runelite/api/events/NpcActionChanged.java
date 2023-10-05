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

import net.runelite.api.NPCComposition;

/**
 * An event where an action of an {@link NPCComposition} has changed.
 */
public class NpcActionChanged
{
	/**
	 * The NPC composition that has been changed.
	 */
	private NPCComposition npcComposition;
	/**
	 * The raw index of the modified action.
	 */
	private int idx;

    public NpcActionChanged() {
    }

    public NPCComposition getNpcComposition() {
        return this.npcComposition;
    }

    public int getIdx() {
        return this.idx;
    }

    public void setNpcComposition(NPCComposition npcComposition) {
        this.npcComposition = npcComposition;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof NpcActionChanged)) return false;
        final NpcActionChanged other = (NpcActionChanged) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$npcComposition = this.getNpcComposition();
        final Object other$npcComposition = other.getNpcComposition();
        if (this$npcComposition == null ? other$npcComposition != null : !this$npcComposition.equals(other$npcComposition))
            return false;
        if (this.getIdx() != other.getIdx()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof NpcActionChanged;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $npcComposition = this.getNpcComposition();
        result = result * PRIME + ($npcComposition == null ? 43 : $npcComposition.hashCode());
        result = result * PRIME + this.getIdx();
        return result;
    }

    public String toString() {
        return "NpcActionChanged(npcComposition=" + this.getNpcComposition() + ", idx=" + this.getIdx() + ")";
    }
}
