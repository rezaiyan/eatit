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
import kotlinx.android.synthetic.main.view_food.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class FoodRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val greenColor = context.resources.getColor(R.color.green)
    private val blackColor = context.resources.getColor(R.color.black)

    init {
        View.inflate(context, R.layout.view_food, this)
    }

    @ModelProp
    fun food(food: Food) {
        food.apply {
            Glide.with(titleTextView.context).load(food.photo)
                .into(bannerImageView)
            titleTextView.text = title
            descriptionTextView.text = description
            metadataTextView.text = metadata
            addToCardButton.text = price
        }
    }

    @CallbackProp
    fun clickListener(clickListener: OnClickListener?) {
        addToCardButton.setOnClickListener {
            addToCardButton.updateView(greenColor, blackColor)
            clickListener?.onClick(it)
        }
    }
}
