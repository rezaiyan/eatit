package ir.alirezaiyan.views.extensions

import android.content.res.Resources

fun screenHeight() = Resources.getSystem().displayMetrics.heightPixels

fun appBarHeight(): Int {
    return (screenHeight() * 0.7).toInt()
}

fun dpToPx(value: Float) = value * Resources.getSystem().displayMetrics.density