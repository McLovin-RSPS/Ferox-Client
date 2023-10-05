package com.ferox.draw.widget

import com.ferox.cache.graphics.widget.Widget
import org.reflections.Reflections
import org.reflections.scanners.MethodAnnotationsScanner
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder
import java.lang.reflect.Method
import kotlin.system.measureTimeMillis

object InterfaceLoader {

    fun init() {
        val reflections = Reflections(
                ConfigurationBuilder().setUrls(ClasspathHelper.forPackage("com.ferox.draw.widget.impl")).setScanners(
                        MethodAnnotationsScanner()
                )
        )

        val methods: Set<Method> = reflections.getMethodsAnnotatedWith(WidgetInterface::class.java)

        load(methods)

        println("Interfaces read -> ${methods.size}")
    }

    fun load(methods : Set<Method>)  {
        val time = measureTimeMillis {
            methods.forEach {
                val widgetClass = Class.forName(it.declaringClass.name).newInstance()
                val m2: Method = widgetClass::class.java.getDeclaredMethod(it.name)
                m2.invoke(widgetClass)
            }
        }
        println("Custom Interfaces Completed in $time ms")
        println("Width: ${Widget.cache[12502].disabledSprite.width}")
    }

}
