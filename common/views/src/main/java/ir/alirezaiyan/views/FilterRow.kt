package ir.alirezaiyan.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import ir.alirezaiyan.eatit.views.R
import ir.alirezaiyan.eatit.domain.home.Filter
import kotlinx.android.synthetic.main.view_filter.view.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class FilterRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.view_filter, this)
    }

    @ModelProp
    fun setFilters(filters : List<Filter>) {
        filterRecyclerView.withModels {
            filters.forEach {
                chipRow {
                    id(it.id)
                    text(it.title)
                }
            }
        }
    }
}
