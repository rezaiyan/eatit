package ir.alirezaiyan.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.bumptech.glide.Glide
import ir.alirezaiyan.eatit.views.R
import ir.alirezaiyan.eatit.domain.home.Food
import ir.alirezaiyan.eatit.resources.updateView
import kotlinx.android.synthetic.main.view_cart.view.*
import kotlinx.android.synthetic.main.view_food.view.*
import kotlinx.android.synthetic.main.view_food.view.bannerImageView
import kotlinx.android.synthetic.main.view_food.view.titleTextView

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class CartRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_cart, this)
    }

    @ModelProp
    fun food(food: Food) {
        food.apply {
            Glide.with(titleTextView.context).load(food.photo)
                .into(imageImageView)
            titleTextView.text = title
            priceTextView.text = price
        }
    }

    @CallbackProp
    fun removeListener(removeListener: OnClickListener?) {
        removeImageView.setOnClickListener {
            removeListener?.onClick(it)
        }
    }
}
