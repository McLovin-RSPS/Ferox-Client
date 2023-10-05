package com.ferox.draw.widget


import com.ferox.Client
import com.ferox.cache.graphics.widget.Widget
import com.ferox.draw.widget.InterfaceData.components
import com.ferox.draw.widget.InterfaceData.widgetInfo
import com.ferox.utils.Position
import java.awt.Dimension

@DslMarker
private annotation class InterfaceBuilderDslMarker

@DslMarker
private annotation class InterfaceComponentBuilderDslMarker

enum class InterfaceType { MAIN_SCREEN, TAB, CHATBOX }

object InterfaceData {


    data class WidgetInfo(val id : Int, val name : String)
    data class WidgetData(val id : Int, val childs : List<WidgetData>, val name : String)

    val widgetInfo = emptyMap<WidgetInfo, List<WidgetData>>().toMutableMap()
    val components = emptyMap<Int, WidgetData>().toMutableMap()

}

@InterfaceBuilderDslMarker
open class InterfaceBuilder(var id: Int, initWidth : Int, initHeight : Int) : InterfaceComponent() {

    var type: InterfaceType = InterfaceType.MAIN_SCREEN

    var name: String = ""


    /**
     * Parent interface
     */
    val widget : Widget = when (type) {
        InterfaceType.MAIN_SCREEN -> Widget.addInterface(id)
        InterfaceType.CHATBOX -> Widget.addTabInterface(id)
        InterfaceType.TAB -> Widget.addTabInterface(id)
    }

    /**
     * All child widgets attached to this interface
     */
    val children = linkedSetOf<InterfaceComponent>()

    fun setType(builder: () -> InterfaceType) { this.type = builder() }

    fun setName(builder: () -> String) { this.name = builder() }

    private fun totalChildren(): Int {
        var total = children.size
        children.filterIsInstance<InterfaceScroll>().forEach { total += (it).children.size }
        return total
    }

    fun nextId() = totalChildren() + 1 + id

}

@InterfaceBuilderDslMarker
open class InterfaceScroll(startID: Int, var width: Int, var height: Int, var max: Int) : InterfaceBuilder(startID,0,0)

fun InterfaceBuilder.scroll(width: Int, height: Int, max: Int, newScroll : Boolean = false, builder: InterfaceScroll.() -> Unit) {

    val bld = InterfaceScroll(nextId(), width, height, max)
    bld.widget.scrollMax = max
    bld.widget.width = width
    bld.widget.height = height
    bld.widget.newScroller = newScroll
    bld.componentId = nextId()
    builder.invoke(bld)

    bld.widget.totalChildren(bld.children.size)

    bld.children.forEachIndexed { idx, child ->
        Widget.setBounds(child.componentId, child.position.x, child.position.y, idx, bld.widget)
    }

    children.add(bld)
}

@InterfaceComponentBuilderDslMarker
open class InterfaceComponent {
    var componentId: Int = 0
    var size: Dimension = Dimension()
    var position: Position = Position(0, 0)
    fun position(bld: () -> Position) {
        this.position = bld()
    }

}

fun InterfaceBuilder.widget(builder: WidgetComponent.() -> Unit) {
    val bld = WidgetComponent()
    builder.invoke(bld)

    bld.componentId = bld.widgetid
    children.add(bld)
}

@Markers
open class WidgetComponent : InterfaceComponent() {
    var widgetid = 0
    fun widgetID(bld: () -> Int) {
        this.widgetid = bld()
    }
}

fun buildInterface(id: Int,intWidth  : Int = 0,intHeight : Int = 0 ,center : Boolean = false, builder: InterfaceBuilder.() -> Unit): Widget {
    val bld = InterfaceBuilder(id, intWidth,intHeight)
    builder.invoke(bld)
    bld.widget.totalChildren(bld.children.size)

    var padX = 0
    var padY = 0

    if(center) {
        padX = (Client.gamescreen_width - intWidth) / 2
        padY = (Client.gamescreen_height - intHeight) / 2
    }

    bld.children.forEachIndexed { idx, child ->
        Widget.setBounds(child.componentId, padX + child.position.x, padY + child.position.y, idx, bld.widget)
    }

    return bld.widget
}

fun buildInterface(id: Int, offsetX : Int = 0, offsetY : Int = 0,builder: InterfaceBuilder.() -> Unit): Widget {
    val bld = InterfaceBuilder(id,0,0)
    builder.invoke(bld)
    bld.widget.totalChildren(bld.children.size)

    bld.children.forEachIndexed { idx, child ->
        Widget.setBounds(child.componentId, offsetX + child.position.x, offsetY + child.position.y, idx, bld.widget)
    }

    return bld.widget
}
