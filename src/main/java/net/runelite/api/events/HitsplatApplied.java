/*
 * Copyright (c) 2018, Woox <https://github.com/wooxsolo>
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
import net.runelite.api.Hitsplat;

/**
 * An event called when a {@link Hitsplat} is processed on an {@link Actor}.
 * <p>
 * This event is called regardless of whether or not the hitsplat was
 * rendered. An example of this occuring would be if the actor has 4
 * visible hitsplats.
 */
public class HitsplatApplied
{
	/**
	 * The actor the hitsplat was applied to.
	 */
	private Actor actor;
	/**
	 * The applied hitsplat.
	 */
	private Hitsplat hitsplat;

    public HitsplatApplied() {
    }

    public Actor getActor() {
        return this.actor;
    }

    public Hitsplat getHitsplat() {
        return this.hitsplat;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public void setHitsplat(Hitsplat hitsplat) {
        this.hitsplat = hitsplat;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof HitsplatApplied)) return false;
        final HitsplatApplied other = (HitsplatApplied) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$actor = this.getActor();
        final Object other$actor = other.getActor();
        if (this$actor == null ? other$actor != null : !this$actor.equals(other$actor)) return false;
        final Object this$hitsplat = this.getHitsplat();
        final Object other$hitsplat = other.getHitsplat();
        if (this$hitsplat == null ? other$hitsplat != null : !this$hitsplat.equals(other$hitsplat)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof HitsplatApplied;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $actor = this.getActor();
        result = result * PRIME + ($actor == null ? 43 : $actor.hashCode());
        final Object $hitsplat = this.getHitsplat();
        result = result * PRIME + ($hitsplat == null ? 43 : $hitsplat.hashCode());
        return result;
    }

    public String toString() {
        return "HitsplatApplied(actor=" + this.getActor() + ", hitsplat=" + this.getHitsplat() + ")";
    }
}