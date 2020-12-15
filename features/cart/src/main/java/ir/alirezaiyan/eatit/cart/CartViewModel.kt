package ir.alirezaiyan.eatit.cart


import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.FragmentViewModelContext
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import ir.alirezaiyan.eatit.domain.home.Food
import ir.alirezaiyan.eatit.domain.home.usecase.GetCartUseCase
import ir.alirezaiyan.eatit.domain.home.usecase.RemoveFromCartUseCase

class CartViewModel @AssistedInject constructor(
    @Assisted state: CartState,
    private val getCartUseCase: GetCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase
) : BaseMvRxViewModel<CartState>(state) {

    init {
        loadCart()
    }


    private fun loadCart() {
        getCartUseCase().execute {
            copy(request = it)
        }
    }

    fun removeFromCart(food: Food) {
        removeFromCartUseCase(food).execute {
            if (it() != null)
                copy(request = it)
            else {
                copy(request = it)
            }
        }
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(state: CartState): CartViewModel
    }

    companion object : MvRxViewModelFactory<CartViewModel, CartState> {
        override fun create(viewModelContext: ViewModelContext, state: CartState): CartViewModel? =
            (viewModelContext as FragmentViewModelContext)
                .fragment<HostFragment>()
                .viewModelFactory.create(state)
    }
}
