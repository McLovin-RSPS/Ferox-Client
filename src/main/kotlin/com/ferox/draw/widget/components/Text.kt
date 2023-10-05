package com.ferox.draw.widget.components

import com.ferox.cache.graphics.font.AdvancedFont
import com.ferox.cache.graphics.widget.Widget
import com.ferox.draw.widget.InterfaceBuilder
import com.ferox.draw.widget.InterfaceComponent
import com.ferox.draw.widget.Markers
import com.ferox.utils.Position

object Text {


    /**
     * Stores the id and height of the 4 different font types
     * @param id - font index id
     * @param height - font height
     */
    enum class FontType(val id: Int, val height: Int) {
        SMALL(0, 9), REGULAR(1, 11), BOLD(2, 11), FANCY(3, 12),FANCY_LARGE(4,18);
    }

    fun InterfaceBuilder.text(builder: TextComponent.() -> Unit) {
        val bld = TextComponent()
        builder.invoke(bld)
        bld.componentId = nextId()
        addText(bld.componentId, bld.text, Widget.fonts, bld.type.id, bld.color, bld.center, bld.shadow,bld.lineSpace,bld.width,bld.tooltip,bld.hover)
        children.add(bld)
    }

    fun InterfaceBuilder.text(component : TextComponent) {
        component.componentId = nextId()
        addText(component.componentId, component.text, Widget.fonts, component.type.id, component.color, component.center, component.shadow, component.lineSpace, component.width,component.tooltip,component.hover)
        children.add(component)
    }

    fun InterfaceBuilder.texts(amount: Int, builder: TextComponent.(Int) -> Unit) {
        repeat(amount) { text { builder(it) } }
    }

    fun InterfaceBuilder.texts(amount: Int, rowSize: Int, builder: TextComponent.(Int, Int) -> Unit) {
        repeat(amount) { text { builder(it % rowSize, Math.floorDiv(it, rowSize)) } }
    }

    fun InterfaceBuilder.texts(amount: Int, rowSize: Int, padX: Int = 0, padY: Int = 0, builder: TextComponent.(id : Int) -> Unit) {
        repeat(amount) {
            val bld = TextComponent()
            val row = (it % rowSize)
            val col = Math.floorDiv(it, rowSize)
            val width = Widget.fonts[bld.type.id].get_width(bld.text) + padX
            val height = bld.type.height + padY

            builder.invoke(bld,it)
            bld.componentId = nextId()
            bld.position = Position(bld.position.x + (row * width), bld.position.y + (col * height))

            addText(bld.componentId, bld.text, Widget.fonts, bld.type.id, bld.color, bld.center, bld.shadow,bld.lineSpace,bld.width,bld.tooltip,bld.hover)
            children.add(bld)

        }
    }

    fun addText(
        id: Int,
        text: String?,
        tda: Array<AdvancedFont>,
        idx: Int,
        color: Int,
        center: Boolean,
        shadow: Boolean,
        lineSpace: Int,
        width: Int,
        tooltip: String
    ) {
        Widget.cache[id] = Widget()
        val tab = Widget.cache[id]
        tab.parent = id
        tab.id = id
        tab.type = 4
        tab.optionType = 1
        tab.width = 0
        tab.contentType = 0
        tab.opacity = 0
        tab.hoverType = -1
        tab.fontType = FontType.values().filter { it.id == idx }.first()
        tab.centerText = center
        tab.textShadow = shadow
        tab.text_type = tda[idx]
        tab.defaultText = text
        tab.secondaryText = ""
        tab.textColour = color
        tab.secondaryColor = 0
        tab.defaultHoverColor = 0
        tab.secondaryHoverColor = 0
        tab.lineSpace = lineSpace
        if (!tooltip.isEmpty()) {
            tab.width = tda[idx].get_width(text)
            tab.height = FontType.values()[idx].height
            tab.optionType = 1
            tab.tooltip = tooltip
        }
    }

    fun addText(id: Int, text: String, tda: Array<AdvancedFont>, idx: Int, color: Int, center: Boolean, shadow: Boolean, lineSpace: Int, width: Int, tooltip: String, hover: Int) {
        Widget.cache[id] = Widget()
        val tab = Widget.cache[id]
        tab.parent = id
        tab.id = id
        tab.type = 4
        tab.optionType = 43535
        tab.contentType = 0
        tab.opacity = 0
        tab.hoverType = -1
        tab.centerText = center
        tab.fontType = FontType.values().filter { it.id == idx }.first()
        tab.textShadow = shadow
        tab.text_type = tda[idx]
        tab.defaultText = text
        tab.secondaryText = ""
        tab.textColour = color
        tab.secondaryColor = hover
        tab.defaultHoverColor = hover
        tab.secondaryHoverColor = hover
        tab.textHoverColor = hover
        tab.defaultHoverColor = hover
        tab.lineSpace = lineSpace
        tab.wrapSize = width
        if (tooltip.isNotEmpty()) {
            tab.width = width
            tab.height = 11
            tab.optionType = 43535
            tab.tooltip = tooltip
        }
    }

    @Markers
    open class TextComponent : InterfaceComponent() {
        var text = ""
        var type = FontType.SMALL
        var color = 0
        var hover = 0
        var center = true
        var shadow = true
        var lineSpace = 0
        var width = 0
        var tooltip = ""

        fun text(bld: () -> String) {
            this.text = bld()
        }
        fun type(bld: () -> FontType) {
            this.type = bld()
        }
        fun color(bld: () -> Int) {
            this.color = bld()
        }
        fun hover(bld: () -> Int) {
            this.hover = bld()
        }
        fun space(bld: () -> Int) {
            this.lineSpace = bld()
        }
        fun center(bld: () -> Boolean) {
            this.center = bld()
        }
        fun shadow(bld: () -> Boolean) {
            this.shadow = bld()
        }
        fun width(bld: () -> Int) {
            this.width = bld()
        }
        fun tooltip(bld: () -> String) {
            this.tooltip = bld()
        }

    }


}
