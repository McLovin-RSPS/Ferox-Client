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

/**
 * An event where the graphic of an {@link Actor} has changed.
 * <p>
 * The graphic the player has changed to can be obtained using
 * {@link Actor#getGraphic()}.
 * <p>
 * Examples of when this event may trigger include:
 * <ul>
 *     <li>Casting a magic spell</li>
 *     <li>Using a fairy ring</li>
 *     <li>Breaking a teleport tab</li>
 * </ul>
 *
 * @see net.runelite.api.GraphicID
 */
public class GraphicChanged
{
	/**
	 * The actor that has had their graphic changed.
	 */
	private Actor actor;

    public GraphicChanged() {
    }

    public Actor getActor() {
        return this.actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof GraphicChanged)) return false;
        final GraphicChanged other = (GraphicChanged) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$actor = this.getActor();
        final Object other$actor = other.getActor();
        if (this$actor == null ? other$actor != null : !this$actor.equals(other$actor)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof GraphicChanged;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $actor = this.getActor();
        result = result * PRIME + ($actor == null ? 43 : $actor.hashCode());
        return result;
    }

    public String toString() {
        return "GraphicChanged(actor=" + this.getActor() + ")";
    }
}