package ir.alirezaiyan.eatit.home

import com.airbnb.mvrx.*
import ir.alirezaiyan.network.model.Home

data class HomeState(val request: Async<Home> = Uninitialized)
    : MvRxState{

    val isLoading: Boolean
        get() = request is Incomplete

}