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

import net.runelite.api.Projectile;
import net.runelite.api.coords.LocalPoint;

/**
 * An event called whenever a {@link Projectile} has moved towards a point.
 * <p>
 * For projectiles that target the ground, this event is only triggered
 * once (ie. AoE from Lizardman Shaman).
 */
public class ProjectileMoved
{
	/**
	 * The projectile being moved.
	 */
	private Projectile projectile;
	/**
	 * The target location of the projectile.
	 */
	private LocalPoint position;
	/**
	 * The z-axis target location of the projectile.
	 */
	private int z;

    public ProjectileMoved() {
    }

    public Projectile getProjectile() {
        return this.projectile;
    }

    public LocalPoint getPosition() {
        return this.position;
    }

    public int getZ() {
        return this.z;
    }

    public void setProjectile(Projectile projectile) {
        this.projectile = projectile;
    }

    public void setPosition(LocalPoint position) {
        this.position = position;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ProjectileMoved)) return false;
        final ProjectileMoved other = (ProjectileMoved) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$projectile = this.getProjectile();
        final Object other$projectile = other.getProjectile();
        if (this$projectile == null ? other$projectile != null : !this$projectile.equals(other$projectile))
            return false;
        final Object this$position = this.getPosition();
        final Object other$position = other.getPosition();
        if (this$position == null ? other$position != null : !this$position.equals(other$position)) return false;
        if (this.getZ() != other.getZ()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ProjectileMoved;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $projectile = this.getProjectile();
        result = result * PRIME + ($projectile == null ? 43 : $projectile.hashCode());
        final Object $position = this.getPosition();
        result = result * PRIME + ($position == null ? 43 : $position.hashCode());
        result = result * PRIME + this.getZ();
        return result;
    }

    public String toString() {
        return "ProjectileMoved(projectile=" + this.getProjectile() + ", position=" + this.getPosition() + ", z=" + this.getZ() + ")";
    }
}
