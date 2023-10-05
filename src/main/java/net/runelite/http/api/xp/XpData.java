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
package net.runelite.http.api.xp;

import java.time.Instant;

public class XpData
{
	private Instant time;

	private int attack_xp;
	private int defence_xp;
	private int strength_xp;
	private int hitpoints_xp;
	private int ranged_xp;
	private int prayer_xp;
	private int magic_xp;
	private int cooking_xp;
	private int woodcutting_xp;
	private int fletching_xp;
	private int fishing_xp;
	private int firemaking_xp;
	private int crafting_xp;
	private int smithing_xp;
	private int mining_xp;
	private int herblore_xp;
	private int agility_xp;
	private int thieving_xp;
	private int slayer_xp;
	private int farming_xp;
	private int runecraft_xp;
	private int hunter_xp;
	private int construction_xp;

	private int overall_rank;
	private int attack_rank;
	private int defence_rank;
	private int strength_rank;
	private int hitpoints_rank;
	private int ranged_rank;
	private int prayer_rank;
	private int magic_rank;
	private int cooking_rank;
	private int woodcutting_rank;
	private int fletching_rank;
	private int fishing_rank;
	private int firemaking_rank;
	private int crafting_rank;
	private int smithing_rank;
	private int mining_rank;
	private int herblore_rank;
	private int agility_rank;
	private int thieving_rank;
	private int slayer_rank;
	private int farming_rank;
	private int runecraft_rank;
	private int hunter_rank;
	private int construction_rank;

    public XpData() {
    }

    public Instant getTime() {
        return this.time;
    }

    public int getAttack_xp() {
        return this.attack_xp;
    }

    public int getDefence_xp() {
        return this.defence_xp;
    }

    public int getStrength_xp() {
        return this.strength_xp;
    }

    public int getHitpoints_xp() {
        return this.hitpoints_xp;
    }

    public int getRanged_xp() {
        return this.ranged_xp;
    }

    public int getPrayer_xp() {
        return this.prayer_xp;
    }

    public int getMagic_xp() {
        return this.magic_xp;
    }

    public int getCooking_xp() {
        return this.cooking_xp;
    }

    public int getWoodcutting_xp() {
        return this.woodcutting_xp;
    }

    public int getFletching_xp() {
        return this.fletching_xp;
    }

    public int getFishing_xp() {
        return this.fishing_xp;
    }

    public int getFiremaking_xp() {
        return this.firemaking_xp;
    }

    public int getCrafting_xp() {
        return this.crafting_xp;
    }

    public int getSmithing_xp() {
        return this.smithing_xp;
    }

    public int getMining_xp() {
        return this.mining_xp;
    }

    public int getHerblore_xp() {
        return this.herblore_xp;
    }

    public int getAgility_xp() {
        return this.agility_xp;
    }

    public int getThieving_xp() {
        return this.thieving_xp;
    }

    public int getSlayer_xp() {
        return this.slayer_xp;
    }

    public int getFarming_xp() {
        return this.farming_xp;
    }

    public int getRunecraft_xp() {
        return this.runecraft_xp;
    }

    public int getHunter_xp() {
        return this.hunter_xp;
    }

    public int getConstruction_xp() {
        return this.construction_xp;
    }

    public int getOverall_rank() {
        return this.overall_rank;
    }

    public int getAttack_rank() {
        return this.attack_rank;
    }

    public int getDefence_rank() {
        return this.defence_rank;
    }

    public int getStrength_rank() {
        return this.strength_rank;
    }

    public int getHitpoints_rank() {
        return this.hitpoints_rank;
    }

    public int getRanged_rank() {
        return this.ranged_rank;
    }

    public int getPrayer_rank() {
        return this.prayer_rank;
    }

    public int getMagic_rank() {
        return this.magic_rank;
    }

    public int getCooking_rank() {
        return this.cooking_rank;
    }

    public int getWoodcutting_rank() {
        return this.woodcutting_rank;
    }

    public int getFletching_rank() {
        return this.fletching_rank;
    }

    public int getFishing_rank() {
        return this.fishing_rank;
    }

    public int getFiremaking_rank() {
        return this.firemaking_rank;
    }

    public int getCrafting_rank() {
        return this.crafting_rank;
    }

    public int getSmithing_rank() {
        return this.smithing_rank;
    }

    public int getMining_rank() {
        return this.mining_rank;
    }

    public int getHerblore_rank() {
        return this.herblore_rank;
    }

    public int getAgility_rank() {
        return this.agility_rank;
    }

    public int getThieving_rank() {
        return this.thieving_rank;
    }

    public int getSlayer_rank() {
        return this.slayer_rank;
    }

    public int getFarming_rank() {
        return this.farming_rank;
    }

    public int getRunecraft_rank() {
        return this.runecraft_rank;
    }

    public int getHunter_rank() {
        return this.hunter_rank;
    }

    public int getConstruction_rank() {
        return this.construction_rank;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public void setAttack_xp(int attack_xp) {
        this.attack_xp = attack_xp;
    }

    public void setDefence_xp(int defence_xp) {
        this.defence_xp = defence_xp;
    }

    public void setStrength_xp(int strength_xp) {
        this.strength_xp = strength_xp;
    }

    public void setHitpoints_xp(int hitpoints_xp) {
        this.hitpoints_xp = hitpoints_xp;
    }

    public void setRanged_xp(int ranged_xp) {
        this.ranged_xp = ranged_xp;
    }

    public void setPrayer_xp(int prayer_xp) {
        this.prayer_xp = prayer_xp;
    }

    public void setMagic_xp(int magic_xp) {
        this.magic_xp = magic_xp;
    }

    public void setCooking_xp(int cooking_xp) {
        this.cooking_xp = cooking_xp;
    }

    public void setWoodcutting_xp(int woodcutting_xp) {
        this.woodcutting_xp = woodcutting_xp;
    }

    public void setFletching_xp(int fletching_xp) {
        this.fletching_xp = fletching_xp;
    }

    public void setFishing_xp(int fishing_xp) {
        this.fishing_xp = fishing_xp;
    }

    public void setFiremaking_xp(int firemaking_xp) {
        this.firemaking_xp = firemaking_xp;
    }

    public void setCrafting_xp(int crafting_xp) {
        this.crafting_xp = crafting_xp;
    }

    public void setSmithing_xp(int smithing_xp) {
        this.smithing_xp = smithing_xp;
    }

    public void setMining_xp(int mining_xp) {
        this.mining_xp = mining_xp;
    }

    public void setHerblore_xp(int herblore_xp) {
        this.herblore_xp = herblore_xp;
    }

    public void setAgility_xp(int agility_xp) {
        this.agility_xp = agility_xp;
    }

    public void setThieving_xp(int thieving_xp) {
        this.thieving_xp = thieving_xp;
    }

    public void setSlayer_xp(int slayer_xp) {
        this.slayer_xp = slayer_xp;
    }

    public void setFarming_xp(int farming_xp) {
        this.farming_xp = farming_xp;
    }

    public void setRunecraft_xp(int runecraft_xp) {
        this.runecraft_xp = runecraft_xp;
    }

    public void setHunter_xp(int hunter_xp) {
        this.hunter_xp = hunter_xp;
    }

    public void setConstruction_xp(int construction_xp) {
        this.construction_xp = construction_xp;
    }

    public void setOverall_rank(int overall_rank) {
        this.overall_rank = overall_rank;
    }

    public void setAttack_rank(int attack_rank) {
        this.attack_rank = attack_rank;
    }

    public void setDefence_rank(int defence_rank) {
        this.defence_rank = defence_rank;
    }

    public void setStrength_rank(int strength_rank) {
        this.strength_rank = strength_rank;
    }

    public void setHitpoints_rank(int hitpoints_rank) {
        this.hitpoints_rank = hitpoints_rank;
    }

    public void setRanged_rank(int ranged_rank) {
        this.ranged_rank = ranged_rank;
    }

    public void setPrayer_rank(int prayer_rank) {
        this.prayer_rank = prayer_rank;
    }

    public void setMagic_rank(int magic_rank) {
        this.magic_rank = magic_rank;
    }

    public void setCooking_rank(int cooking_rank) {
        this.cooking_rank = cooking_rank;
    }

    public void setWoodcutting_rank(int woodcutting_rank) {
        this.woodcutting_rank = woodcutting_rank;
    }

    public void setFletching_rank(int fletching_rank) {
        this.fletching_rank = fletching_rank;
    }

    public void setFishing_rank(int fishing_rank) {
        this.fishing_rank = fishing_rank;
    }

    public void setFiremaking_rank(int firemaking_rank) {
        this.firemaking_rank = firemaking_rank;
    }

    public void setCrafting_rank(int crafting_rank) {
        this.crafting_rank = crafting_rank;
    }

    public void setSmithing_rank(int smithing_rank) {
        this.smithing_rank = smithing_rank;
    }

    public void setMining_rank(int mining_rank) {
        this.mining_rank = mining_rank;
    }

    public void setHerblore_rank(int herblore_rank) {
        this.herblore_rank = herblore_rank;
    }

    public void setAgility_rank(int agility_rank) {
        this.agility_rank = agility_rank;
    }

    public void setThieving_rank(int thieving_rank) {
        this.thieving_rank = thieving_rank;
    }

    public void setSlayer_rank(int slayer_rank) {
        this.slayer_rank = slayer_rank;
    }

    public void setFarming_rank(int farming_rank) {
        this.farming_rank = farming_rank;
    }

    public void setRunecraft_rank(int runecraft_rank) {
        this.runecraft_rank = runecraft_rank;
    }

    public void setHunter_rank(int hunter_rank) {
        this.hunter_rank = hunter_rank;
    }

    public void setConstruction_rank(int construction_rank) {
        this.construction_rank = construction_rank;
    }

    public String toString() {
        return "XpData(time=" + this.getTime() + ", attack_xp=" + this.getAttack_xp() + ", defence_xp=" + this.getDefence_xp() + ", strength_xp=" + this.getStrength_xp() + ", hitpoints_xp=" + this.getHitpoints_xp() + ", ranged_xp=" + this.getRanged_xp() + ", prayer_xp=" + this.getPrayer_xp() + ", magic_xp=" + this.getMagic_xp() + ", cooking_xp=" + this.getCooking_xp() + ", woodcutting_xp=" + this.getWoodcutting_xp() + ", fletching_xp=" + this.getFletching_xp() + ", fishing_xp=" + this.getFishing_xp() + ", firemaking_xp=" + this.getFiremaking_xp() + ", crafting_xp=" + this.getCrafting_xp() + ", smithing_xp=" + this.getSmithing_xp() + ", mining_xp=" + this.getMining_xp() + ", herblore_xp=" + this.getHerblore_xp() + ", agility_xp=" + this.getAgility_xp() + ", thieving_xp=" + this.getThieving_xp() + ", slayer_xp=" + this.getSlayer_xp() + ", farming_xp=" + this.getFarming_xp() + ", runecraft_xp=" + this.getRunecraft_xp() + ", hunter_xp=" + this.getHunter_xp() + ", construction_xp=" + this.getConstruction_xp() + ", overall_rank=" + this.getOverall_rank() + ", attack_rank=" + this.getAttack_rank() + ", defence_rank=" + this.getDefence_rank() + ", strength_rank=" + this.getStrength_rank() + ", hitpoints_rank=" + this.getHitpoints_rank() + ", ranged_rank=" + this.getRanged_rank() + ", prayer_rank=" + this.getPrayer_rank() + ", magic_rank=" + this.getMagic_rank() + ", cooking_rank=" + this.getCooking_rank() + ", woodcutting_rank=" + this.getWoodcutting_rank() + ", fletching_rank=" + this.getFletching_rank() + ", fishing_rank=" + this.getFishing_rank() + ", firemaking_rank=" + this.getFiremaking_rank() + ", crafting_rank=" + this.getCrafting_rank() + ", smithing_rank=" + this.getSmithing_rank() + ", mining_rank=" + this.getMining_rank() + ", herblore_rank=" + this.getHerblore_rank() + ", agility_rank=" + this.getAgility_rank() + ", thieving_rank=" + this.getThieving_rank() + ", slayer_rank=" + this.getSlayer_rank() + ", farming_rank=" + this.getFarming_rank() + ", runecraft_rank=" + this.getRunecraft_rank() + ", hunter_rank=" + this.getHunter_rank() + ", construction_rank=" + this.getConstruction_rank() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof XpData)) return false;
        final XpData other = (XpData) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getAttack_xp() != other.getAttack_xp()) return false;
        if (this.getDefence_xp() != other.getDefence_xp()) return false;
        if (this.getStrength_xp() != other.getStrength_xp()) return false;
        if (this.getHitpoints_xp() != other.getHitpoints_xp()) return false;
        if (this.getRanged_xp() != other.getRanged_xp()) return false;
        if (this.getPrayer_xp() != other.getPrayer_xp()) return false;
        if (this.getMagic_xp() != other.getMagic_xp()) return false;
        if (this.getCooking_xp() != other.getCooking_xp()) return false;
        if (this.getWoodcutting_xp() != other.getWoodcutting_xp()) return false;
        if (this.getFletching_xp() != other.getFletching_xp()) return false;
        if (this.getFishing_xp() != other.getFishing_xp()) return false;
        if (this.getFiremaking_xp() != other.getFiremaking_xp()) return false;
        if (this.getCrafting_xp() != other.getCrafting_xp()) return false;
        if (this.getSmithing_xp() != other.getSmithing_xp()) return false;
        if (this.getMining_xp() != other.getMining_xp()) return false;
        if (this.getHerblore_xp() != other.getHerblore_xp()) return false;
        if (this.getAgility_xp() != other.getAgility_xp()) return false;
        if (this.getThieving_xp() != other.getThieving_xp()) return false;
        if (this.getSlayer_xp() != other.getSlayer_xp()) return false;
        if (this.getFarming_xp() != other.getFarming_xp()) return false;
        if (this.getRunecraft_xp() != other.getRunecraft_xp()) return false;
        if (this.getHunter_xp() != other.getHunter_xp()) return false;
        if (this.getConstruction_xp() != other.getConstruction_xp()) return false;
        if (this.getOverall_rank() != other.getOverall_rank()) return false;
        if (this.getAttack_rank() != other.getAttack_rank()) return false;
        if (this.getDefence_rank() != other.getDefence_rank()) return false;
        if (this.getStrength_rank() != other.getStrength_rank()) return false;
        if (this.getHitpoints_rank() != other.getHitpoints_rank()) return false;
        if (this.getRanged_rank() != other.getRanged_rank()) return false;
        if (this.getPrayer_rank() != other.getPrayer_rank()) return false;
        if (this.getMagic_rank() != other.getMagic_rank()) return false;
        if (this.getCooking_rank() != other.getCooking_rank()) return false;
        if (this.getWoodcutting_rank() != other.getWoodcutting_rank()) return false;
        if (this.getFletching_rank() != other.getFletching_rank()) return false;
        if (this.getFishing_rank() != other.getFishing_rank()) return false;
        if (this.getFiremaking_rank() != other.getFiremaking_rank()) return false;
        if (this.getCrafting_rank() != other.getCrafting_rank()) return false;
        if (this.getSmithing_rank() != other.getSmithing_rank()) return false;
        if (this.getMining_rank() != other.getMining_rank()) return false;
        if (this.getHerblore_rank() != other.getHerblore_rank()) return false;
        if (this.getAgility_rank() != other.getAgility_rank()) return false;
        if (this.getThieving_rank() != other.getThieving_rank()) return false;
        if (this.getSlayer_rank() != other.getSlayer_rank()) return false;
        if (this.getFarming_rank() != other.getFarming_rank()) return false;
        if (this.getRunecraft_rank() != other.getRunecraft_rank()) return false;
        if (this.getHunter_rank() != other.getHunter_rank()) return false;
        if (this.getConstruction_rank() != other.getConstruction_rank()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof XpData;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getAttack_xp();
        result = result * PRIME + this.getDefence_xp();
        result = result * PRIME + this.getStrength_xp();
        result = result * PRIME + this.getHitpoints_xp();
        result = result * PRIME + this.getRanged_xp();
        result = result * PRIME + this.getPrayer_xp();
        result = result * PRIME + this.getMagic_xp();
        result = result * PRIME + this.getCooking_xp();
        result = result * PRIME + this.getWoodcutting_xp();
        result = result * PRIME + this.getFletching_xp();
        result = result * PRIME + this.getFishing_xp();
        result = result * PRIME + this.getFiremaking_xp();
        result = result * PRIME + this.getCrafting_xp();
        result = result * PRIME + this.getSmithing_xp();
        result = result * PRIME + this.getMining_xp();
        result = result * PRIME + this.getHerblore_xp();
        result = result * PRIME + this.getAgility_xp();
        result = result * PRIME + this.getThieving_xp();
        result = result * PRIME + this.getSlayer_xp();
        result = result * PRIME + this.getFarming_xp();
        result = result * PRIME + this.getRunecraft_xp();
        result = result * PRIME + this.getHunter_xp();
        result = result * PRIME + this.getConstruction_xp();
        result = result * PRIME + this.getOverall_rank();
        result = result * PRIME + this.getAttack_rank();
        result = result * PRIME + this.getDefence_rank();
        result = result * PRIME + this.getStrength_rank();
        result = result * PRIME + this.getHitpoints_rank();
        result = result * PRIME + this.getRanged_rank();
        result = result * PRIME + this.getPrayer_rank();
        result = result * PRIME + this.getMagic_rank();
        result = result * PRIME + this.getCooking_rank();
        result = result * PRIME + this.getWoodcutting_rank();
        result = result * PRIME + this.getFletching_rank();
        result = result * PRIME + this.getFishing_rank();
        result = result * PRIME + this.getFiremaking_rank();
        result = result * PRIME + this.getCrafting_rank();
        result = result * PRIME + this.getSmithing_rank();
        result = result * PRIME + this.getMining_rank();
        result = result * PRIME + this.getHerblore_rank();
        result = result * PRIME + this.getAgility_rank();
        result = result * PRIME + this.getThieving_rank();
        result = result * PRIME + this.getSlayer_rank();
        result = result * PRIME + this.getFarming_rank();
        result = result * PRIME + this.getRunecraft_rank();
        result = result * PRIME + this.getHunter_rank();
        result = result * PRIME + this.getConstruction_rank();
        return result;
    }
}
