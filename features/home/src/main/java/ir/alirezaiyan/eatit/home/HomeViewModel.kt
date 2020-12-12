package ir.alirezaiyan.eatit.home

import com.airbnb.mvrx.BaseMvRxViewModel
import com.airbnb.mvrx.FragmentViewModelContext
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import ir.alirezaiyan.eatit.domain.home.usecase.HomeUseCase

class HomeViewModel @AssistedInject constructor(
    @Assisted state: HomeState,
    private val homeUseCase: HomeUseCase
) : BaseMvRxViewModel<HomeState>(state) {

    init {
        loadHome()
    }

    private fun loadHome() {
        homeUseCase().execute{
            copy(request = it)
        }
    }

    fun onSwipeAction() {
        loadHome()
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
