package ir.alirezaiyan.eatit.resources

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton


fun Button.updateView(selectedColor: Int, unselectedColor: Int) {
    val title = text
    text = "Added +1"
    updateBackgroundColor(unselectedColor, selectedColor)
    postDelayed({
        updateBackgroundColor(selectedColor, unselectedColor)
        text = title
    }, 1000)
}

private fun Button.updateBackgroundColor(
    unselectedColor: Int,
    selectedColor: Int
) {
    val colorAnimation =
        ValueAnimator.ofObject(ArgbEvaluator(), unselectedColor, selectedColor)
    colorAnimation.duration = 100
    colorAnimation.addUpdateListener { animator -> setBackgroundColor(animator.animatedValue as Int) }
    colorAnimation.start()
}

var TextView.textWithAnim: String
    get() {
        return text.toString()
    }
    set(value) {
        setTextWithAnimation(value)
    }


private fun TextView.setTextWithAnimation(input: String) {
    animate()
        .scaleX(0.95f).scaleY(0.95f)
        .setDuration(200)
        .withEndAction {
            text = input
            animate().scaleX(1.05f).scaleY(1.05f).setDuration(200)
                .withEndAction {
                    animate().scaleX(1.0f).scaleY(1.0f).duration = 100
                }
        }
}

fun View.animateFab() {
    this.scale(0F).withEndAction {
        this.scale(1F).start()
    }.start()
}

fun View.scale(value: Float): ViewPropertyAnimator =
    animate().scaleX(value).scaleY(value).setDuration(100L)

fun View.scaleNow(value: Float, duration: Long = 150) {
    animate().scaleX(value).scaleY(value).setDuration(duration).start()
}
