package net.runelite.client.plugins.hdosrs.lighting

import net.runelite.api.coords.WorldPoint
import net.runelite.api.events.ItemDespawned
import net.runelite.client.plugins.hdosrs.lighting.LightManager.sceneLights

object LightUtils {

    fun removeItemLight(itemDespawned: ItemDespawned) {
        val light = sceneLights.find { WorldPoint(it.x - 1,it.y - 1,it.plane) == WorldPoint(itemDespawned.tile.localLocation.x,itemDespawned.tile.localLocation.y,itemDespawned.tile.plane) }
        sceneLights.remove(light)
    }

}
