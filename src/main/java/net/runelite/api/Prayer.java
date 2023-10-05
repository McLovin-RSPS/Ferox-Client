/*
 * Decompiled with CFR 0.150.
 */
package net.runelite.api;

public enum Prayer {
	THICK_SKIN(Varbits.PRAYER_THICK_SKIN, 5.0, 5609, 5632, 22638),
	BURST_OF_STRENGTH(Varbits.PRAYER_BURST_OF_STRENGTH, 5.0, 5610, 5633, 22639),
	CLARITY_OF_THOUGHT(Varbits.PRAYER_CLARITY_OF_THOUGHT, 5.0, 5611, 5634, 22640),
	SHARP_EYE(Varbits.PRAYER_SHARP_EYE, 5.0, 19812, 19813, 22641),
	MYSTIC_WILL(Varbits.PRAYER_MYSTIC_WILL, 5.0, 19814, 19815, 22642),
	ROCK_SKIN(Varbits.PRAYER_ROCK_SKIN, 10.0, 5612, 5635, 20253),
	SUPERHUMAN_STRENGTH(Varbits.PRAYER_SUPERHUMAN_STRENGTH, 10.0, 5613, 5636, 20254),
	IMPROVED_REFLEXES(Varbits.PRAYER_IMPROVED_REFLEXES, 10.0, 5614, 5637, 20255),
	RAPID_RESTORE(Varbits.PRAYER_RAPID_RESTORE, 1.6666666666666667, 5615, 5638, 20256),
	RAPID_HEAL(Varbits.PRAYER_RAPID_HEAL, 3.3333333333333335, 5616, 5639, 20257),
	PROTECT_ITEM(Varbits.PRAYER_PROTECT_ITEM, 3.3333333333333335, 5617, 5640, 21141),
	HAWK_EYE(Varbits.PRAYER_HAWK_EYE, 10.0, 19816, 19817, 21142),
	MYSTIC_LORE(Varbits.PRAYER_MYSTIC_LORE, 10.0, 19818, 19820, 21143),
	STEEL_SKIN(Varbits.PRAYER_STEEL_SKIN, 20.0, 5618, 5641, 21144),
	ULTIMATE_STRENGTH(Varbits.PRAYER_ULTIMATE_STRENGTH, 20.0, 5619, 5642, 21145),
	INCREDIBLE_REFLEXES(Varbits.PRAYER_INCREDIBLE_REFLEXES, 20.0, 5620, 5643, 21146),
	PROTECT_FROM_MAGIC(Varbits.PRAYER_PROTECT_FROM_MAGIC, 20.0, 5621, 5644, 21147),
	PROTECT_FROM_MISSILES(Varbits.PRAYER_PROTECT_FROM_MISSILES, 20.0, 5622, 686, 21148),
	PROTECT_FROM_MELEE(Varbits.PRAYER_PROTECT_FROM_MELEE, 20.0, 5623, 5645, 21149),
	EAGLE_EYE(Varbits.PRAYER_EAGLE_EYE, 20.0, 19821, 19822, 21150),
	MYSTIC_MIGHT(Varbits.PRAYER_MYSTIC_MIGHT, 20.0, 19823, 19824, 21135),
	RETRIBUTION(Varbits.PRAYER_RETRIBUTION, 5.0, 683, 5649, 21136),
	REDEMPTION(Varbits.PRAYER_REDEMPTION, 10.0, 684, 5647, 21137),
	SMITE(Varbits.PRAYER_SMITE, 30.0, 685, 5648, 21138),
	PRESERVE(Varbits.PRAYER_PRESERVE, 3.3333333333333335, 39401, 39402, 39403),
	CHIVALRY(Varbits.PRAYER_CHIVALRY, 40.0, 19825, 19826, 21139),
	PIETY(Varbits.PRAYER_PIETY, 40.0, 19827, 19828, 21140),
	RIGOUR(Varbits.PRAYER_RIGOUR, 40.0, 39404, 39405, 39406),
	AUGURY(Varbits.PRAYER_AUGURY, 40.0, 39407, 39408, 39409);

	private final Varbits varbit;
	private final double drainRate;
	private final int widgetId;
	private final int spriteId;
	private final int tooltipId;

	private Prayer(Varbits varbit, double drainRate) {
		this.varbit = varbit;
		this.drainRate = drainRate;
		this.widgetId = 0;
		this.spriteId = 0;
		this.tooltipId = 0;
	}

	private Prayer(Varbits varbit, double drainRate, int widgetId, int spriteId, int tooltipId) {
		this.varbit = varbit;
		this.drainRate = drainRate;
		this.widgetId = widgetId;
		this.spriteId = spriteId;
		this.tooltipId = tooltipId;
	}

	public Varbits getVarbit() {
		return this.varbit;
	}

	public double getDrainRate() {
		return this.drainRate;
	}

	public int getWidgetId() {
		return this.widgetId;
	}

	public int getSpriteId() {
		return this.spriteId;
	}

	public int getTooltipId() {
		return this.tooltipId;
	}
}

