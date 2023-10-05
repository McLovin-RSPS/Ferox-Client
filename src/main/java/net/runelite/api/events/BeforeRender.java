package net.runelite.api.events;

public class BeforeRender {
    public BeforeRender() {
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof BeforeRender)) return false;
        final BeforeRender other = (BeforeRender) o;
        if (!other.canEqual((Object) this)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BeforeRender;
    }

    public int hashCode() {
        int result = 1;
        return result;
    }

    public String toString() {
        return "BeforeRender()";
    }
}
