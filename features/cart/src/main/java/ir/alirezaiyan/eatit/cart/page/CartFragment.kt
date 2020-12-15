package ir.alirezaiyan.eatit.cart.page

import com.airbnb.mvrx.parentFragmentViewModel
import ir.alirezaiyan.eatit.cart.CartViewModel
import ir.alirezaiyan.views.cartRow
import ir.alirezaiyan.views.priceRow
import ir.alirezaiyan.views.screen.ContainerFragment
import ir.alirezaiyan.views.screen.simpleController

class CartFragment : ContainerFragment() {

    private val viewModel: CartViewModel by parentFragmentViewModel(CartViewModel::class)

    override fun controller() = simpleController(viewModel) { state ->
        var totalPrice = 0
        state.request()?.forEach {
            totalPrice += it.getRawPrice()
            cartRow {
                id(it.id)
                food(it)
                removeListener { _ -> viewModel.removeFromCart(it) }
            }
        }
        if (totalPrice > 0) {
            priceRow {
                id("price")
                price(totalPrice.toString())
            }
        }
    }

}