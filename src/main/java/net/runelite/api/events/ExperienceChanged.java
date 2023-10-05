/*
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
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
 * An event where the experience level of a {@link Skill} has been modified.
 */
public class ExperienceChanged
{
	/**
	 * The modified skill.
	 */
	private Skill skill;

    public ExperienceChanged() {
    }

    public Skill getSkill() {
        return this.skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ExperienceChanged)) return false;
        final ExperienceChanged other = (ExperienceChanged) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$skill = this.getSkill();
        final Object other$skill = other.getSkill();
        if (this$skill == null ? other$skill != null : !this$skill.equals(other$skill)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ExperienceChanged;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $skill = this.getSkill();
        result = result * PRIME + ($skill == null ? 43 : $skill.hashCode());
        return result;
    }

    public String toString() {
        return "ExperienceChanged(skill=" + this.getSkill() + ")";
    }
}
