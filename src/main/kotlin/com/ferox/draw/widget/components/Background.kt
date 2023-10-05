package com.ferox.draw.widget.components

import com.ferox.Client
import com.ferox.cache.graphics.SimpleImage
import com.ferox.cache.graphics.widget.Widget
import com.ferox.cache.graphics.widget.Widget.*
import com.ferox.draw.widget.Colors
import com.ferox.draw.widget.InterfaceBuilder
import com.ferox.draw.widget.InterfaceComponent
import com.ferox.draw.widget.Markers
import com.ferox.draw.widget.components.Button.button
import com.ferox.draw.widget.components.CloseButton.close
import com.ferox.draw.widget.components.Divider.divider
import com.ferox.draw.widget.components.Text.text
import com.ferox.utils.MathUtils
import com.ferox.utils.Position


object Background {

    fun InterfaceBuilder.background(builder: BackgroundComponent.() -> Unit) {
        val bld = BackgroundComponent()
        builder.invoke(bld)
        bld.componentId = nextId()
        addBackgroundImage(bld.componentId, bld.width,bld.height,bld.thin,bld.style)
        children.add(bld)

        if(bld.title.isNotEmpty()) {
            text {
                text { bld.title }
                color { Colors.HEADER }
                type { Text.FontType.BOLD }
                position { Position(bld.width /2 ,11) }
            }
        }

        if(bld.devider) {
            divider {
                size { bld.width - 11 }
                position { Position(6,29) }
            }
        }

        if(!bld.closeButton.equals(CloseButton.CloseButtons.NONE)) {
            close {
                type { bld.closeButton }
                position { Position(bld.width - 30 + if(bld.closeButton == CloseButton.CloseButtons.OSRS_SMALL) 5 else 0,10) }
            }
        }

    }

    enum class ForceType {
        OSRS,
        NEW,
        NONE
    }

    private fun addBackgroundImage(id: Int, width: Int, height: Int, thin: Boolean, force : ForceType) {
        val tab: Widget = addInterface(id)
        tab.id = id
        tab.parent = id
        tab.type = TYPE_SPRITE
        tab.optionType = 0
        tab.contentType = 0
        tab.opacity = 0.toByte()
        when (force) {
            ForceType.NONE -> {
                tab.disabledSprite = buildBackground(width, height, thin,false)
                tab.enabledSprite = buildBackground(width, height, thin,true)
            }
            ForceType.OSRS -> {
                tab.disabledSprite = buildBackground(width, height, thin,true)
                tab.enabledSprite = buildBackground(width, height, thin,true)
            }
            ForceType.NEW -> {
                tab.disabledSprite = buildBackground(width, height, thin,false)
                tab.enabledSprite = buildBackground(width, height, thin,false)
            }
        }
        tab.width = width
        tab.height = height
    }

    private fun buildBackground(width: Int, height: Int, skinnyborder: Boolean, osrs : Boolean): SimpleImage {
        val pixels = Array(height) { IntArray(width) }

        // Background
        fillPixels(pixels, Client.spriteCache.get(if(osrs) 1887  else 1887), 0, 0, width, height)

        // Top border
        fillPixels(pixels, Client.spriteCache.get(if(osrs) 1896  else 1879), 32, 0, width - 32, 7)

        // Right border
        fillPixels(pixels, Client.spriteCache.get(if(osrs) 1889  else 1881), 0, 32, 7, height - 32)

        // Left border
        fillPixels(pixels, Client.spriteCache.get(if(osrs) 1890  else 1880), width - 7, 32, width, height - 32)

        // Bottom border
        fillPixels(pixels, Client.spriteCache.get(if(osrs) 1888  else 1882), 32, height - 7, width - 32, height)

        // Top left corner
        MathUtils.insertPixels(pixels, Client.spriteCache.get(if(osrs) 1892  else 1883), 0, 0)

        // Top right corner
        MathUtils.insertPixels(pixels, Client.spriteCache.get(if(osrs) 1893  else 1884), width - 32, 0)

        // Bottom left corner
        MathUtils.insertPixels(pixels, Client.spriteCache.get(if(osrs) 1894  else 1885), 0, height - 32)

        // Bottom right corner
        MathUtils.insertPixels(pixels, Client.spriteCache.get(if(osrs) 1895  else 1186), width - 32, height - 32)
        return SimpleImage(width, height, 0, 0, MathUtils.d2Tod1(pixels))
    }


    @Markers
    open class BackgroundComponent : InterfaceComponent() {
        var width : Int = 0
        var height : Int = 0
        var thin : Boolean = true
        var title : String = ""
        var closeButton : CloseButton.CloseButtons = CloseButton.CloseButtons.NONE
        var devider : Boolean = false
        var style : ForceType = ForceType.NONE
        fun width(bld: () -> Int) {
            this.width = bld()
        }
        fun force(bld: () -> ForceType) {
            this.style = bld()
        }
        fun height(bld: () -> Int) {
            this.height = bld()
        }
        fun title(bld: () -> String) {
            this.title = bld()
        }
        fun closeButton(bld: () -> CloseButton.CloseButtons) {
            this.closeButton = bld()
        }
        fun devider(bld: () -> Boolean) {
            this.devider = bld()
        }

        fun thin(bld: () -> Boolean) {
            this.thin = bld()
        }
    }

}
