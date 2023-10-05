package com.ferox.draw.widget.components

import com.ferox.Client
import com.ferox.cache.graphics.SimpleImage
import com.ferox.cache.graphics.widget.Widget
import com.ferox.cache.graphics.widget.Widget.*
import com.ferox.draw.widget.InterfaceBuilder
import com.ferox.draw.widget.InterfaceComponent
import com.ferox.draw.widget.Markers
import com.ferox.util.Utils.d2Tod1

object Divider {

    fun InterfaceBuilder.divider(builder: DeviderComponent.() -> Unit) {
        val bld = DeviderComponent()
        builder.invoke(bld)
        bld.componentId = nextId()
        addDividerImage(bld.componentId, bld.width,bld.across)
        children.add(bld)
    }

    fun addDividerImage(id: Int, width: Int, across: Boolean) {
        val tab: Widget = addInterface(id)
        tab.id = id
        tab.parent = id
        tab.type = TYPE_SPRITE
        tab.optionType = 0
        tab.contentType = 0
        tab.opacity = 0.toByte()
        tab.enabledSprite = if (!across) buildDeviderHeight(width,true) else buildDevider(width,true)
        tab.disabledSprite = if (!across) buildDeviderHeight(width,false) else buildDevider(width,false)
        tab.width = width
        tab.height = 7
    }



    fun buildDevider(width: Int,osrs : Boolean): SimpleImage {
        val pixels = Array(7) { IntArray(width) }
        fillPixels(pixels, Client.spriteCache.get(if(osrs) 1896  else 384), 0, 0, width, 7)
        return SimpleImage(width, 7, 0, 0, d2Tod1(pixels))
    }

    fun buildDeviderHeight(height: Int,osrs : Boolean): SimpleImage {
        val pixels = Array(height) { IntArray(7) }
        fillPixels(pixels, Client.spriteCache.get(if(osrs) 1896  else 386), 0, 0, 7, height)
        return SimpleImage(7, height, 0, 0, d2Tod1(pixels))
    }


    @Markers
    open class DeviderComponent : InterfaceComponent() {
        var width : Int = 0
        var across : Boolean = true
        fun size(bld: () -> Int) {
            this.width = bld()
        }
        fun across(bld: () -> Boolean) {
            this.across = bld()
        }
    }

}
