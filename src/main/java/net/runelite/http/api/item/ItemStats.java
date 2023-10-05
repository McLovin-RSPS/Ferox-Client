package net.runelite.http.api.item;

import com.google.gson.annotations.SerializedName;

public final class ItemStats
{
    private final boolean quest;
    private final boolean equipable;
    private final double weight;
    @SerializedName("ge_limit")
    private final int geLimit;

    private final ItemEquipmentStats equipment;

    public ItemStats(boolean quest, boolean equipable, double weight, int geLimit, ItemEquipmentStats equipment) {
        this.quest = quest;
        this.equipable = equipable;
        this.weight = weight;
        this.geLimit = geLimit;
        this.equipment = equipment;
    }

    public ItemStats subtract(ItemStats other)
    {
        if (other == null)
        {
            return this;
        }

        final double newWeight = weight - other.weight;
        final ItemEquipmentStats newEquipment;


        if (other.equipment != null)
        {
            final ItemEquipmentStats equipment = this.equipment != null
                    ? this.equipment
                    : new ItemEquipmentStats.ItemEquipmentStatsBuilder().build();

            newEquipment = new ItemEquipmentStats.ItemEquipmentStatsBuilder()
                    .slot(equipment.getSlot())
                    .astab(equipment.getAstab() - other.equipment.getAstab())
                    .aslash(equipment.getAslash() - other.equipment.getAslash())
                    .acrush(equipment.getAcrush() - other.equipment.getAcrush())
                    .amagic(equipment.getAmagic() - other.equipment.getAmagic())
                    .arange(equipment.getArange() - other.equipment.getArange())
                    .dstab(equipment.getDstab() - other.equipment.getDstab())
                    .dslash(equipment.getDslash() - other.equipment.getDslash())
                    .dcrush(equipment.getDcrush() - other.equipment.getDcrush())
                    .dmagic(equipment.getDmagic() - other.equipment.getDmagic())
                    .drange(equipment.getDrange() - other.equipment.getDrange())
                    .str(equipment.getStr() - other.equipment.getStr())
                    .rstr(equipment.getRstr() - other.equipment.getRstr())
                    .mdmg(equipment.getMdmg() - other.equipment.getMdmg())
                    .prayer(equipment.getPrayer() - other.equipment.getPrayer())
                    .aspeed(equipment.getAspeed() - other.equipment.getAspeed())
                    .build();
        }
        else
        {
            newEquipment = equipment;
        }

        return new ItemStats(quest, equipable, newWeight, 0, newEquipment);
    }

    public boolean isQuest() {
        return this.quest;
    }

    public boolean isEquipable() {
        return this.equipable;
    }

    public double getWeight() {
        return this.weight;
    }

    public int getGeLimit() {
        return this.geLimit;
    }

    public ItemEquipmentStats getEquipment() {
        return this.equipment;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ItemStats)) return false;
        final ItemStats other = (ItemStats) o;
        if (this.isQuest() != other.isQuest()) return false;
        if (this.isEquipable() != other.isEquipable()) return false;
        if (Double.compare(this.getWeight(), other.getWeight()) != 0) return false;
        if (this.getGeLimit() != other.getGeLimit()) return false;
        final Object this$equipment = this.getEquipment();
        final Object other$equipment = other.getEquipment();
        if (this$equipment == null ? other$equipment != null : !this$equipment.equals(other$equipment)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + (this.isQuest() ? 79 : 97);
        result = result * PRIME + (this.isEquipable() ? 79 : 97);
        final long $weight = Double.doubleToLongBits(this.getWeight());
        result = result * PRIME + (int) ($weight >>> 32 ^ $weight);
        result = result * PRIME + this.getGeLimit();
        final Object $equipment = this.getEquipment();
        result = result * PRIME + ($equipment == null ? 43 : $equipment.hashCode());
        return result;
    }

    public String toString() {
        return "ItemStats(quest=" + this.isQuest() + ", equipable=" + this.isEquipable() + ", weight=" + this.getWeight() + ", geLimit=" + this.getGeLimit() + ", equipment=" + this.getEquipment() + ")";
    }
}