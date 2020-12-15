package ir.alirezaiyan.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
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

    private val greenColor = ResourcesCompat.getColor(resources, R.color.green, null)
    private val blackColor = ResourcesCompat.getColor(resources, R.color.black, null)

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

    private var clickTime = 0L

    @CallbackProp
    fun clickListener(clickListener: OnClickListener?) {
        addToCardButton.setOnClickListener {
            if (System.currentTimeMillis() - 1000 > clickTime) {
                clickTime = System.currentTimeMillis()
                addToCardButton.updateView(greenColor, blackColor)
                clickListener?.onClick(it)
            }

        }
    }
}
