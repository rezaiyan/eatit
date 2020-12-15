package ir.alirezaiyan.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import ir.alirezaiyan.eatit.views.R
import ir.alirezaiyan.views.extensions.toPriceSpannable
import kotlinx.android.synthetic.main.view_price.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class PriceRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_price, this)
    }

    @TextProp
    fun price(price: CharSequence) {
        val valueTitle = "Value: "
        val spannableString = "$valueTitle$price usd"
            .toPriceSpannable(valueTitle, "$price usd")

        priceTextView.text = spannableString
    }

}
