package ir.alirezaiyan.eatit.cart

import com.airbnb.mvrx.*
import ir.alirezaiyan.eatit.domain.home.Food

data class CartState(
    val request: Async<List<Food>> = Uninitialized
) : MvRxState