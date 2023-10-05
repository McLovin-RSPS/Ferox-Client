package com.ferox.draw.widget.impl

import com.ferox.draw.widget.WidgetInterface
import com.ferox.draw.widget.buildInterface
import com.ferox.draw.widget.components.Background.background
import com.ferox.draw.widget.components.Button.buttons
import com.ferox.draw.widget.components.CloseButton
import com.ferox.draw.widget.components.Rasterizer.rasterizer
import com.ferox.draw.widget.components.Rasterizer.rasterizers
import com.ferox.draw.widget.components.Sprites.sprite
import com.ferox.draw.widget.components.Text.texts
import com.ferox.draw.widget.scroll
import com.ferox.utils.Position

class PresetManager {

    @WidgetInterface(24236)
    fun presetManager() {
        buildInterface(24236,437,214,false) {

            background {
                width { 437 }
                height { 314 }
                closeButton { CloseButton.CloseButtons.OSRS_SMALL }
                devider { true }
                title { "Preset Manager" }
                position { Position(0,0) }
            }

            sprite {
                id { 1871 }
                position { Position(8,7) }
            }

            buttons(2,1,0,4) { idx ->
                normal { 1874 }
                hover { 1875 }
                tooltip { listOf("Edit this Preset","Load this Preset")[idx] }
                position { Position(150,244) }
                text {
                    text { listOf("Edit this Preset","Load this Preset")[idx] }
                    center { false }
                    color { 0x8A8A8A }
                    position { Position(30,8) }
                }
            }

            sprite {
                id { 1877 }
                position { Position(156,248) }
            }

            sprite {
                id { 1878 }
                position { Position(154,276) }
            }

            rasterizer {
                width { 136 }
                height { 98 }
                setColours { listOf(0x000000,0x73675C,0x43392B) }
                position { Position(10,38) }
            }

            rasterizer {
                width { 136 }
                height { 98 }
                setColours { listOf(0x000000,0x73675C,0x43392B) }
                position { Position(10,140) }
            }

            rasterizer {
                width { 155 }
                height { 266 }
                setColours { listOf(0x000000,0x73675C,0x43392B) }
                position { Position(272,38) }
            }

            rasterizers(28,4,37,37) { idx ->
                width { 38 }
                height { 38 }
                setColours { listOf(0x000000,0x73675C,0x4B4131) }
                setColourHovers { listOf(0x000000,0x73675C,0x5E5444) }
                tooltip { "Slot $idx" }
                position { Position(275,41) }
            }

            scroll(116,94,100) {
                position { Position(12,40) }

                rasterizers(9,1,37,20) { idx ->
                    width { 116 }
                    height { 20 }

                    val col1 = if(idx % 2 == 0) 0x43392B else 0x382F21
                    val col2 = if(idx % 2 == 0) 0x695F51 else 0x4A4133

                    setColour { col1 }
                    setColourHover { col2 }
                    tooltip { "Preset: $idx" }
                    position { Position(0,0) }
                }

                texts(9,1,37,11) { idx ->
                    text { "Main - Melee: $idx" }
                    center { false }
                    color { 0x8A8A8A }
                    position { Position(3,5) }
                }

            }

            scroll(116,94,100) {

                rasterizers(12,1,37,20) { idx ->
                    width { 116 }
                    height { 20 }

                    val col1 = if(idx % 2 == 0) 0x43392B else 0x382F21
                    val col2 = if(idx % 2 == 0) 0x695F51 else 0x4A4133

                    setColour { col1 }
                    setColourHover { col2 }
                    tooltip { "Make Preset" }
                    position { Position(0,0) }
                }

                texts(12,1,37,11) { idx ->
                    text { "Click to Create Preset: $idx" }
                    center { false }
                    color { 0x8A8A8A }
                    position { Position(3,5) }
                }

                position { Position(12,142) }
            }

            rasterizers(2,1,0,34) { idx ->
                width { 136 }
                height { 29 }
                setColours { listOf(0x000000,0x73675C,0x2F271B) }
                setColourHovers { listOf(0x000000,0x73675C,0x423A2E) }
                tooltip { listOf("Ancients","Regular")[idx] }
                position { Position(10,242) }
            }

            rasterizers(2,1,0,34) {
                width { 1 }
                height { 25 }
                setColour { 0x73675C }
                position { Position(38,244) }
            }

            rasterizers(2,1,0,34) {
                width { 1 }
                height { 25 }
                setColour { 0x000000 }
                position { Position(39,244) }
            }

            sprite {
                id { 1873 }
                position { Position(13,245) }
            }

            sprite {
                id { 1876 }
                position { Position(13,278) }
            }

            texts(2,1,0,24) { idx ->
                text { listOf("Ancients","Regular")[idx] }
                color { 0x979797 }
                position { Position(37 + (104 / 2),251) }
            }



        }
    }

}
