package ir.alirezaiyan.eatit.home

import com.airbnb.mvrx.*
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import ir.alirezaiyan.eatit.domain.home.usecase.AddToCartUseCase
import ir.alirezaiyan.eatit.domain.home.usecase.GetCartUseCase
import ir.alirezaiyan.eatit.domain.home.usecase.HomeUseCase
import ir.alirezaiyan.eatit.domain.home.Food

class HomeViewModel @AssistedInject constructor(
    @Assisted state: HomeState,
    private val homeUseCase: HomeUseCase,
    private val getCartUseCase: GetCartUseCase,
    private val addToCartUseCase: AddToCartUseCase
) : BaseMvRxViewModel<HomeState>(state) {

    init {
        loadHome()
        loadCart()
    }

    private fun loadHome() {
        homeUseCase().execute {
            copy(request = it)
        }
    }


    fun loadCart() {
        getCartUseCase().execute {
            copy(request = request, cartItemCount = Success(it()?.size ?: 0))
        }
    }

    fun addToCart(food: Food) {
        addToCartUseCase(food).execute {
            if (it() != null)
                copy(cartItemCount = Success(it()?.size ?: 0))
            else {
                copy()
            }
        }
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(state: HomeState): HomeViewModel
    }

    companion object : MvRxViewModelFactory<HomeViewModel, HomeState> {
        override fun create(viewModelContext: ViewModelContext, state: HomeState): HomeViewModel? =
            (viewModelContext as FragmentViewModelContext)
                .fragment<HomeFragment>()
                .viewModelFactory.create(state)
    }
}
