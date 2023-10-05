package com.ferox.draw.widget.components

import com.ferox.Client
import com.ferox.cache.graphics.widget.Widget
import com.ferox.cache.graphics.widget.Widget.cache
import com.ferox.draw.widget.InterfaceBuilder
import com.ferox.draw.widget.InterfaceComponent
import com.ferox.draw.widget.Markers
import com.ferox.utils.Position

object Button {

    fun InterfaceBuilder.button(builder: ButtonComponent.() -> Unit) {

        val bld = ButtonComponent()
        builder.invoke(bld)
        bld.componentId = nextId()
        addButton(bld.componentId, bld.spriteNormal,bld.spriteHover,bld.tooltip)
        children.add(bld)

        if(bld.textCopmponent != null) {
            val tbld = Text.TextComponent()
            bld.textCopmponent?.invoke(tbld)
            tbld.componentId = nextId()

            tbld.position = tbld.position + bld.position

            Text.addText(tbld.componentId, tbld.text, Widget.fonts, tbld.type.id, tbld.color, tbld.center, tbld.shadow,tbld.lineSpace,tbld.width,tbld.tooltip)
            children.add(tbld)
        }

        if(bld.spriteComponent != null) {
            val tbld = Sprites.SpriteComponent()
            bld.spriteComponent?.invoke(tbld)
            tbld.componentId = nextId()

            tbld.position = tbld.position + bld.position
            Sprites.addSprite(bld.componentId, tbld.spriteId,tbld.hd)
            children.add(tbld)
        }

    }

    fun InterfaceBuilder.buttons(amount : Int, builder: ButtonComponent.(Int) -> Unit) {
        repeat(amount) { button { builder(it) } }
    }

    fun InterfaceBuilder.buttons(amount : Int, rowSize : Int, builder: ButtonComponent.(Int, Int) -> Unit) {
        repeat(amount) { button { builder(it % rowSize, Math.floorDiv(it, rowSize)) } }
    }

    fun InterfaceBuilder.buttons(amount : Int, rowSize : Int, padX : Int = 0, padY : Int = 0, add : Boolean = true, builder: ButtonComponent.(id : Int) -> Unit) {

        repeat(amount) {

            val bld = ButtonComponent()
            builder.invoke(bld,it)

            val row = (it % rowSize)
            val col = Math.floorDiv(it, rowSize)

            val width = if(add) Client.spriteCache.get(bld.spriteNormal).width + padX else Client.spriteCache.get(bld.spriteNormal).width - padX
            val height = if(add) Client.spriteCache.get(bld.spriteNormal).height + padY else Client.spriteCache.get(bld.spriteNormal).height - padY

            bld.componentId = nextId()
            bld.position = Position(bld.position.x + (row  * width), bld.position.y + (col * height))

            if (bld.tooltips.isNotEmpty()) {
                addButton(bld.componentId, bld.spriteNormal,bld.spriteHover,bld.tooltips)
            } else {
                addButton(bld.componentId, bld.spriteNormal,bld.spriteHover,bld.tooltip)
            }

            children.add(bld)

            if(bld.textCopmponent != null) {
                val tbld = Text.TextComponent()
                bld.textCopmponent?.invoke(tbld)
                tbld.componentId = nextId()

                tbld.position = tbld.position + bld.position

                Text.addText(tbld.componentId, tbld.text, Widget.fonts, tbld.type.id, tbld.color, tbld.center, tbld.shadow,tbld.lineSpace,tbld.width,tbld.tooltip)
                children.add(tbld)
            }

            if(bld.spriteComponent != null) {
                val tbld = Sprites.SpriteComponent()
                bld.spriteComponent?.invoke(tbld)
                tbld.componentId = nextId()

                tbld.position = tbld.position + bld.position

                Sprites.addSprite(bld.componentId, tbld.spriteId,tbld.hd)
                children.add(tbld)
            }
        }

    }

    fun addButton(id: Int, normal: Int, hover: Int, tooltip: String) {
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
        if(hover == -1) {
            tab.disabledSprite = null
        } else {
            tab.disabledSprite = Client.spriteCache.get(hover)
        }
        tab.enabledSprite = Client.spriteCache.get(normal)
        tab.width = Client.spriteCache.get(normal).width
        tab.height = Client.spriteCache.get(normal).height
        tab.tooltip = tooltip
    }

    fun addButton(id: Int, normal: Int, hover: Int, tooltip: Array<String>) {
        cache[id] = Widget()
        val tab = cache[id]
        tab.id = id
        tab.parent = id
        tab.type = 43534
        tab.optionType = 43536
        tab.toggled = false
        tab.contentType = 0
        tab.opacity = 0.toByte()
        tab.hoverType = 52
        if(hover == -1) {
            tab.disabledSprite = null
        } else {
            tab.disabledSprite = Client.spriteCache.get(hover)
        }
        tab.enabledSprite = Client.spriteCache.get(normal)
        tab.width = Client.spriteCache.get(normal).width
        tab.height = Client.spriteCache.get(normal).height
        tab.tooltips = tooltip
    }


    @Markers
    open class ButtonComponent : InterfaceComponent() {
        var spriteNormal = 0
        var spriteHover = -1
        var tooltip = ""
        var tooltips = emptyArray<String>()
        var textCopmponent : (Text.TextComponent.() -> Unit)? = null
        var spriteComponent : (Sprites.SpriteComponent.() -> Unit)? = null

        fun normal(bld: () -> Int) {
            this.spriteNormal = bld()
        }
        fun hover(bld: () -> Int) {
            this.spriteHover = bld()
        }
        fun tooltip(bld: () -> String) {
            this.tooltip = bld()
        }

        fun tooltips(bld: () -> Array<String>) {
            this.tooltips = bld()
        }

        fun text(bld: Text.TextComponent.() -> Unit) {
            this.textCopmponent = bld
        }
        fun sprite(bld: Sprites.SpriteComponent.() -> Unit) {
            this.spriteComponent = bld
        }
    }
}
