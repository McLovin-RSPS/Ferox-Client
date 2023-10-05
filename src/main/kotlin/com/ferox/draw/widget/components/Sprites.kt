package com.ferox.draw.widget.components

import com.ferox.Client
import com.ferox.cache.graphics.widget.Widget
import com.ferox.cache.graphics.widget.Widget.cache
import com.ferox.draw.widget.InterfaceBuilder
import com.ferox.draw.widget.InterfaceComponent
import com.ferox.draw.widget.Markers
import com.ferox.utils.Position

object Sprites {

    fun InterfaceBuilder.sprite(builder: SpriteComponent.(id : Int) -> Unit) {
        val bld = SpriteComponent()
        builder.invoke(bld,id)
        bld.componentId = nextId()
        addSprite(bld.componentId, bld.spriteId,bld.hd)
        children.add(bld)
    }

    fun InterfaceBuilder.sprites(amount : Int, builder: SpriteComponent.(Int) -> Unit) {
        repeat(amount) { sprite { builder(it) } }
    }

    fun InterfaceBuilder.sprites(amount : Int, rowSize : Int, builder: SpriteComponent.(Int, Int) -> Unit) {
        repeat(amount) { sprite { builder(it % rowSize, Math.floorDiv(it, rowSize)) } }
    }

    fun InterfaceBuilder.sprites(amount : Int, rowSize : Int, padX : Int = 0, padY : Int = 0, add : Boolean = true, builder: SpriteComponent.(id : Int) -> Unit) {
        repeat(amount) {

            val bld = SpriteComponent()

            builder.invoke(bld,it)
            val row = (it % rowSize)
            val col = Math.floorDiv(it, rowSize)

            val width = if(add) Client.spriteCache[bld.spriteId].width + padX else  Client.spriteCache[bld.spriteId].width - padX
            val height = if(add) Client.spriteCache[bld.spriteId].height + padY else Client.spriteCache[bld.spriteId].height - padY

            bld.componentId = nextId()
            bld.position = Position(bld.position.x + (row  * width), bld.position.y + (col * height))
            addSprite(bld.componentId, bld.spriteId,bld.hd)
            children.add(bld)
        }

    }

    fun addSprite(id: Int, spriteId: Int, hd: Boolean) {
        cache[id] = Widget()
        val rsint = cache[id]
        rsint.id = id
        rsint.parent = id
        rsint.type = 5
        rsint.optionType = 0
        rsint.contentType = 0
        rsint.opacity = 0
        rsint.hoverType = 0
        if (spriteId != -1) {
            rsint.enabledSprite = Client.spriteCache[spriteId]
            rsint.disabledSprite = Client.spriteCache[spriteId]
        }

        rsint.width = Client.spriteCache[spriteId].width
        rsint.height = Client.spriteCache[spriteId].height
        rsint.hightDetail = hd
    }

    @Markers
    open class SpriteComponent : InterfaceComponent() {
        var spriteId = 0
        var hd = true
        fun id(bld: () -> Int) {
            this.spriteId = bld()
        }
        fun hd(bld: () -> Boolean) {
            this.hd = bld()
        }
    }


}
