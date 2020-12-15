package ir.alirezaiyan.eatit.domain.home.usecase

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import ir.alirezaiyan.eatit.domain.home.Food
import ir.alirezaiyan.eatit.domain.home.repo.HomeRepository
import org.junit.Before
import org.junit.Test

class GetCartUseCaseTest {
    private lateinit var usecase: GetCartUseCase
    private val mockRepository: HomeRepository = mockk()

    private val mockResult = mockk<List<Food>>(relaxed = true)

    @Before
    fun setUp() {
        usecase = GetCartUseCase(mockRepository)
    }

    @Test
    fun `usecase calls get cart items from repository`() {

        every { mockRepository.getCartItems() } returns Single.just(mockResult)

        val test = usecase().test()

        verify { mockRepository.getCartItems() }
        test.assertValue(mockResult)
    }

}