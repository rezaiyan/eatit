package ir.alirezaiyan.eatit.domain.home.usecase

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import ir.alirezaiyan.eatit.domain.home.Food
import ir.alirezaiyan.eatit.domain.home.repo.HomeRepository
import org.junit.Before
import org.junit.Test

class AddToCartUseCaseTest {

    private lateinit var usecase: AddToCartUseCase
    private val mockRepository: HomeRepository = mockk()

    private val mockResult = mockk<List<Food>>(relaxed = true)

    @Before
    fun setUp() {
        usecase = AddToCartUseCase(mockRepository)
    }

    @Test
    fun `usecase calls add to cart from repository`() {

        every { mockRepository.addToCart(testFood) } returns Single.just(mockResult)

        val test = usecase(testFood).test()

        verify { mockRepository.addToCart(testFood) }
        test.assertValue(mockResult)
    }

}