/*
 * Decompiled with CFR 0.150.
 */
package net.runelite.api.events;

public final class RegionChanged {
    private final int lastRegionId;
    private final int plane;

    public RegionChanged(int lastRegionId, int plane) {
        this.lastRegionId = lastRegionId;
        this.plane = plane;
    }

    public int getLastRegionId() {
        return this.lastRegionId;
    }

    public int getPlane() {
        return this.plane;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof RegionChanged)) return false;
        final RegionChanged other = (RegionChanged) o;
        if (this.getLastRegionId() != other.getLastRegionId()) return false;
        if (this.getPlane() != other.getPlane()) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getLastRegionId();
        result = result * PRIME + this.getPlane();
        return result;
    }

    public String toString() {
        return "RegionChanged(lastRegionId=" + this.getLastRegionId() + ", plane=" + this.getPlane() + ")";
    }
}

