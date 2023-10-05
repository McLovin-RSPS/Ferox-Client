package com.ferox.utils

import com.ferox.cache.graphics.widget.Widget

object FreeIds {

    fun printFreeIdRange(minimumFreeSlotsAvailable: Int) {
        var start = 0
        var free = 0
        for (i in 0..44999) {
            if (Widget.cache[i] != null) {
                if (start == 0) {
                    start = i
                }
                free++
            } else {
                if (start > 0) {
                    if (free >= minimumFreeSlotsAvailable) {
                        println("Range [" + start + ", " + (i - 1) + "] has " + free + " free slots.")
                    }
                    free = 0
                    start = 0
                }
            }
        }
    }

}
