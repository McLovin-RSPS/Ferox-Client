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

import net.runelite.api.Skill;

/**
 * An event where a players skill level has been temporarily modified.
 * <p>
 * Examples of when this event may trigger include:
 * <ul>
 *     <li>Prayer points draining or being restored at an altar or restoration pool</li>
 *     <li>Positive and negative effects gained from potions (ie. Saradomin brew)</li>
 *     <li>Earning skill points towards a skill</li>
 *     <li>Levelling up a skill</li>
 * </ul>
 * <p>
 * Use {@link net.runelite.api.Client#getBoostedSkillLevel(Skill)} in order to
 * retrieve the newly boosted skill level.
 */
public class BoostedLevelChanged
{
	/**
	 * The skill that has had its level modified.
	 */
	private Skill skill;

    public BoostedLevelChanged() {
    }

    public Skill getSkill() {
        return this.skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof BoostedLevelChanged)) return false;
        final BoostedLevelChanged other = (BoostedLevelChanged) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$skill = this.getSkill();
        final Object other$skill = other.getSkill();
        if (this$skill == null ? other$skill != null : !this$skill.equals(other$skill)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BoostedLevelChanged;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $skill = this.getSkill();
        result = result * PRIME + ($skill == null ? 43 : $skill.hashCode());
        return result;
    }

    public String toString() {
        return "BoostedLevelChanged(skill=" + this.getSkill() + ")";
    }
}
