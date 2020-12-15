package ir.alirezaiyan.eatit.home

import com.airbnb.mvrx.test.MvRxTestRule
import com.airbnb.mvrx.withState
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import ir.alirezaiyan.eatit.domain.home.Food
import ir.alirezaiyan.eatit.domain.home.Home
import ir.alirezaiyan.eatit.domain.home.usecase.AddToCartUseCase
import ir.alirezaiyan.eatit.domain.home.usecase.GetCartUseCase
import ir.alirezaiyan.eatit.domain.home.usecase.HomeUseCase
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val mvrxRule = MvRxTestRule()

    private val mockHomeUseCase = mockk<HomeUseCase>(relaxed = true)
    private val mockGetCartUseCase = mockk<GetCartUseCase>(relaxed = true)
    private val mockAddToCartUseCase = mockk<AddToCartUseCase>(relaxed = true)

    private val mockHomeResult = mockk<Home>(relaxed = true)
    private val mockFoodResult = mockk<List<Food>>(relaxed = true)

    private lateinit var viewModel: HomeViewModel

    private fun createViewModel(homeState: HomeState): HomeViewModel =
        HomeViewModel(homeState, mockHomeUseCase, mockGetCartUseCase, mockAddToCartUseCase)

    @Test
    fun `initial state load calls to get home response`() {
        every { mockHomeUseCase() } returns Single.just(mockHomeResult)

        viewModel = createViewModel(HomeState())
        viewModel.addToCart(testFood)
        verify { mockHomeUseCase() }
        withState(viewModel) { assert(it.request() == mockHomeResult) }
    }

    @Test
    fun `load food list`() {
        every { mockGetCartUseCase() } returns Single.just(mockFoodResult)

        viewModel = createViewModel(HomeState())

        verify { mockGetCartUseCase() }
        withState(viewModel) { assert(it.cartItemCount() == mockFoodResult.size) }
    }


    @Test
    fun `add food to cart test`() {
        every { mockAddToCartUseCase(testFood) } returns Single.just(mockFoodResult)

        viewModel = createViewModel(HomeState())
        viewModel.addToCart(testFood)
        verify { mockAddToCartUseCase(testFood) }
        withState(viewModel) {
            assert(it.cartItemCount() == mockFoodResult.size)
        }
    }

}