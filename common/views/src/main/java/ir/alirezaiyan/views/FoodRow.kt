package ir.alirezaiyan.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.bumptech.glide.Glide
import ir.alirezaiyan.eatit.views.R
import ir.alirezaiyan.network.model.Filter
import ir.alirezaiyan.network.model.Food
import kotlinx.android.synthetic.main.view_filter.view.*
import kotlinx.android.synthetic.main.view_food.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class FoodRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_food, this)
    }

    @ModelProp
    fun food(food : Food) {
        food.apply {
            Glide.with(titleTextView.context).load(food.photo)
                .into(bannerImageView)
            titleTextView.text = title
            descriptionTextView.text = description
            metadataTextView.text = metadata
            addToCardButton.text = price
        }
    }
}
