package net.runelite.client.plugins.hdosrs.lighting;

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.api.ItemID;
import net.runelite.client.plugins.hdosrs.lighting.LightManager.Alignment;
import net.runelite.client.plugins.hdosrs.lighting.LightManager.LightType;

import java.util.Map;

import static net.runelite.api.ItemID.*;

@AllArgsConstructor
@Getter
enum EquipmentLight
{
    FIRE_CAPE(90, Alignment.BACK, 350, 3f, rgb(220, 156, 74), LightType.STATIC, 0, 15, ItemID.FIRE_CAPE, FIRE_CAPE_10566),
    FIRE_MAX_CAPE(90, Alignment.BACK, 150, 6f, rgb(220, 156, 74), LightType.STATIC, 4000, 8, ItemID.FIRE_MAX_CAPE, FIRE_MAX_CAPE_21186),
    INFERNAL_CAPE(90, Alignment.BACK, 250, 9f, rgb(133, 64, 0), LightType.STATIC, 4000, 10, ItemID.INFERNAL_CAPE, INFERNAL_CAPE_21297),
    INFERNAL_MAX_CAPE(90, Alignment.BACK, 200, 6f, rgb(133, 64, 0), LightType.STATIC, 0, 8, ItemID.INFERNAL_MAX_CAPE, INFERNAL_MAX_CAPE_21285),
    LIT_BUG_LANTERN(50, Alignment.LEFT, 250, 6f, rgb(255, 233, 138), LightType.FLICKER, 0, 10, ItemID.LIT_BUG_LANTERN),
    VOLATILE_STAFF(235, Alignment.FRONTRIGHT, 200, 6f, rgb(255, 233, 138), LightType.STATIC, 0, 10, 25517),
    HARMONISED_STAFF(235, Alignment.FRONTRIGHT, 200, 6f, rgb(4, 110, 176), LightType.STATIC, 0, 10, 24423),
    ELDRITCH_STAFF(235, Alignment.FRONTRIGHT, 200, 6f, rgb(4, 201, 57), LightType.STATIC, 0, 10, 24425),
    ZAROS_GODSWORD(275, Alignment.CENTER, 400, 9f, rgb(4, 86, 252), LightType.PULSE, 900, 7, 1337),
    ;

    private final int[] id;
    private final int height;
    private final Alignment alignment;
    private final int size;
    private final float strength;
    private final int rgb;
    private final LightType lightType;
    private final float duration;
    private final float range;

    EquipmentLight(int height, Alignment alignment, int size, float strength, int rgb, LightType lightType, float duration, float range, int... ids)
    {
        this.height = height;
        this.alignment = alignment;
        this.size = size;
        this.strength = strength;
        this.rgb = rgb;
        this.lightType = lightType;
        this.duration = duration;
        this.range = range;
        this.id = ids;
    }

    private static final Map<Integer, EquipmentLight> LIGHTS;

    static
    {
        ImmutableMap.Builder<Integer, EquipmentLight> builder = new ImmutableMap.Builder<>();
        for (EquipmentLight equipmentLight : values())
        {
            for (int id : equipmentLight.id)
            {
                builder.put(id + 512, equipmentLight);
            }
        }
        LIGHTS = builder.build();
    }

    static EquipmentLight find(int id)
    {
        return LIGHTS.get(id);
    }

    private static int rgb(int r, int g, int b)
    {
        return (r << 16) | (g << 8) | b;
    }
}
