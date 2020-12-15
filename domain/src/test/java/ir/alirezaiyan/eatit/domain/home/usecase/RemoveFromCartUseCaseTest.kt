package ir.alirezaiyan.eatit.domain.home.usecase

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import ir.alirezaiyan.eatit.domain.home.Food
import ir.alirezaiyan.eatit.domain.home.repo.HomeRepository
import org.junit.Before
import org.junit.Test

class RemoveFromCartUseCaseTest {
    private lateinit var usecase: RemoveFromCartUseCase
    private val mockRepository: HomeRepository = mockk()

    private val mockResult = mockk<List<Food>>(relaxed = true)

    @Before
    fun setUp() {
        usecase = RemoveFromCartUseCase(mockRepository)
    }

    @Test
    fun `usecase calls remove from cart from repository`() {

        every { mockRepository.removeFromCart(testFood) } returns Single.just(mockResult)

        val test = usecase(testFood).test()

        verify { mockRepository.removeFromCart(testFood) }
        test.assertValue(mockResult)
    }

}