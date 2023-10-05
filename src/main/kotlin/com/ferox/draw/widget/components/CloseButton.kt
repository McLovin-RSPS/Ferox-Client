package com.ferox.draw.widget.components

import com.ferox.Client
import com.ferox.cache.graphics.widget.Widget
import com.ferox.cache.graphics.widget.Widget.cache
import com.ferox.draw.widget.InterfaceBuilder
import com.ferox.draw.widget.InterfaceComponent
import com.ferox.draw.widget.Markers
import com.ferox.utils.Position

object CloseButton {

    enum class CloseButtons(val normal : Int, val hover : Int, height : Int) {
        NONE(-1,-1,-1),
        OSRS_LARGE(107,108,21),
        OSRS_SMALL(913,914,16),
    }

    fun InterfaceBuilder.close(builder: ButtonComponent.() -> Unit) {
        val bld = ButtonComponent()
        builder.invoke(bld)
        bld.componentId = nextId()
        addButton(bld.componentId, bld.type)
        children.add(bld)

    }

    fun addButton(id: Int, type : CloseButtons) {
        cache[id] = Widget()
        val tab = cache[id]
        tab.id = id
        tab.parent = id
        tab.type = 43534
        tab.optionType = 43535
        tab.toggled = false
        tab.contentType = 0
        tab.opacity = 0.toByte()
        tab.hoverType = 52
        tab.disabledSprite = Client.spriteCache.get(type.normal)
        tab.enabledSprite = Client.spriteCache.get(type.hover)
        tab.width = Client.spriteCache.get(type.normal).width
        tab.height = Client.spriteCache.get(type.normal).height
        tab.tooltip = "Close"
    }


    @Markers
    open class ButtonComponent : InterfaceComponent() {
        var type = CloseButtons.OSRS_LARGE

        fun type(bld: () -> CloseButtons) {
            this.type = bld()
        }

    }
}
