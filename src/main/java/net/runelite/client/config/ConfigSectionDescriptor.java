package net.runelite.client.config;

public final class ConfigSectionDescriptor implements ConfigObject
{
    private final String key;
    private final ConfigSection section;

    public ConfigSectionDescriptor(String key, ConfigSection section) {
        this.key = key;
        this.section = section;
    }

    @Override
    public String key()
    {
        return key;
    }

    @Override
    public String name()
    {
        return section.name();
    }

    @Override
    public int position()
    {
        return section.position();
    }

    public String getKey() {
        return this.key;
    }

    public ConfigSection getSection() {
        return this.section;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ConfigSectionDescriptor)) return false;
        final ConfigSectionDescriptor other = (ConfigSectionDescriptor) o;
        final Object this$key = this.getKey();
        final Object other$key = other.getKey();
        if (this$key == null ? other$key != null : !this$key.equals(other$key)) return false;
        final Object this$section = this.getSection();
        final Object other$section = other.getSection();
        if (this$section == null ? other$section != null : !this$section.equals(other$section)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $key = this.getKey();
        result = result * PRIME + ($key == null ? 43 : $key.hashCode());
        final Object $section = this.getSection();
        result = result * PRIME + ($section == null ? 43 : $section.hashCode());
        return result;
    }

    public String toString() {
        return "ConfigSectionDescriptor(key=" + this.getKey() + ", section=" + this.getSection() + ")";
    }
}