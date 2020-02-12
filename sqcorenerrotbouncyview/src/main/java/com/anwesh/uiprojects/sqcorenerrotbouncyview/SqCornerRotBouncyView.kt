package com.anwesh.uiprojects.sqcorenerrotbouncyview

/**
 * Created by anweshmishra on 12/02/20.
 */

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color
import android.content.Context

val nodes : Int = 5
val lines : Int = 4
val rot : Float = 90f
val scGap : Float = 0.02f / lines
val strokeFactor : Int = 90
val sizeFactor : Float = 2.9f
val foreColor : Int = Color.parseColor("#4CAF50")
val backColor : Int = Color.parseColor("#BDBDBD")
val lSizeFactor : Float = 3f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawSqRotBouncy(i : Int, scale : Float, size : Float, paint : Paint) {
    val sf : Float = scale.sinify().divideScale(i, lines)
    val lSize : Float = size / lSizeFactor
    save()
    rotate(rot * sf)
    save()
    translate(size, -size)
    for (j in 0..1) {
        save()
        rotate(rot * j)
        drawLine(0f, 0f, 0f, lSize, paint)
        restore()
    }
    restore()
    restore()
}

fun Canvas.drawCornerRotSqs(scale : Float, size : Float, paint : Paint) {
    for (j in 0..(lines - 1)) {
        drawSqRotBouncy(j, scale, size, paint)
    }
}

fun Canvas.drawSCRBNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    paint.color = foreColor
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.strokeCap = Paint.Cap.ROUND
    save()
    translate(w / 2, gap * (i + 1))
    drawCornerRotSqs(scale, size, paint)
    restore()
}
