package com.ferox.utils

import com.ferox.cache.graphics.SimpleImage
import java.awt.Color

object MathUtils {

    fun getPixelAmt(current : Int, pixels : Int) = (pixels * .01 * current).toInt()

    fun rbgToHex(color : Color) : Int = color.red shl 16 + color.green shl 16 + color.blue

    fun d1Tod2(array: IntArray, width: Int): Array<IntArray> {
        val newArray = Array(array.size / width) { IntArray(width) }
        for (i in array.indices) {
            newArray[i / width][i % width] = array[i]
        }
        return newArray
    }

    fun fillPixels(pixels: Array<IntArray>, color: Int, x: Int, y: Int, width: Int, height: Int) {
        for (j in y until height) {
            for (i in x until width) {
                pixels[j][i] = color
            }
        }
    }

    fun insertPixels(pixels: Array<IntArray>, image: SimpleImage, x: Int, y: Int, ignoreTransparency: Boolean) {
        val imagePixels: Array<IntArray> =  d1Tod2(image.pixels, image.width)
        for (j in y until y + image.height) {
            for (i in x until x + image.width) {
                if (ignoreTransparency && imagePixels[j - y][i - x] == 0) continue
                pixels[j][i] = imagePixels[j - y][i - x]
            }
        }
    }

    fun fillPixels(pixels: Array<IntArray>, image: SimpleImage, x: Int, y: Int, width: Int, height: Int) {
        val imagePixels: Array<IntArray> = d1Tod2(image.pixels, image.width)
        for (j in y until height) {
            for (i in x until width) {
                pixels[j][i] = imagePixels[(j - y) % image.height][(i - x) % image.width]
            }
        }
    }

    fun d2Tod1(array: Array<IntArray>): IntArray {
        val newArray = IntArray(array.size * array[0].size)
        for (i in array.indices) for (j in array[i].indices) {
            newArray[i * array[0].size + j] = array[i][j]
        }
        return newArray
    }


    fun insertPixels(pixels: Array<IntArray>, image: SimpleImage, x: Int, y: Int) {
        val imagePixels: Array<IntArray> = d1Tod2(image.pixels, image.width)
        for (j in y until y + image.height) {
            for (i in x until x + image.width) {
                pixels[j][i] = imagePixels[j - y][i - x]
            }
        }
    }


}
