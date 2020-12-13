package ir.alirezaiyan.eatit.home

import android.os.Bundle
import com.airbnb.mvrx.MvRx
import com.airbnb.mvrx.args
import ir.alirezaiyan.network.model.Category
import ir.alirezaiyan.views.filterRow
import ir.alirezaiyan.views.foodRow
import ir.alirezaiyan.views.screen.ContainerFragment
import ir.alirezaiyan.views.screen.simpleController

class MenuFragment : ContainerFragment() {

    companion object{
        fun create(category : Category) = MenuFragment().also {
            it.arguments = Bundle().apply {
                putParcelable(MvRx.KEY_ARG , category)
            }
        }
    }

    private val category : Category by args()

    override fun controller() = simpleController {
        filterRow {
            id(category.title)
            filters(category.filters)
        }
        category.foods.forEach {
            foodRow {
                id(it.id)
                food(it)
            }
        }
    }
}
