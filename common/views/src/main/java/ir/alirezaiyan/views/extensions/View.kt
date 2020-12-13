package ir.alirezaiyan.views.extensions

import android.view.View

infix fun View.visible(visible: Boolean) =
    if (visible) {
        visibility = View.VISIBLE
    } else {
        visibility = View.GONE
    }

fun View.isVisible() = visibility == View.VISIBLE