package ir.alirezaiyan.views.extensions

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan

fun String.toPriceSpannable(title: String, value: String): CharSequence =
    SpannableString(this).apply {
        val totalLength = title.length + value.length
        setSpan(
            AbsoluteSizeSpan(24, true),
            title.length,
            totalLength,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        setSpan(
            StyleSpan(Typeface.BOLD),
            title.length,
            totalLength,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

    }