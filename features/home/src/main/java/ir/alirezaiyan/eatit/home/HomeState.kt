package ir.alirezaiyan.eatit.home

import com.airbnb.mvrx.*
import ir.alirezaiyan.eatit.domain.home.Home

data class HomeState(
    val request: Async<Home> = Uninitialized,
    val cartItemCount: Async<Int> = Success(0)
) : MvRxState {

    val isLoading: Boolean
        get() = request is Incomplete

}