package ir.alirezaiyan.eatit.domain.home.usecase

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import ir.alirezaiyan.eatit.domain.home.Food
import ir.alirezaiyan.eatit.domain.home.Home
import ir.alirezaiyan.eatit.domain.home.repo.HomeRepository
import org.junit.Before
import org.junit.Test

class HomeUseCaseTest {
    private lateinit var usecase: HomeUseCase
    private val mockRepository: HomeRepository = mockk()

    private val mockResult = mockk<Home>(relaxed = true)

    @Before
    fun setUp() {
        usecase = HomeUseCase(mockRepository)
    }

    @Test
    fun `usecase calls get home response from repository`() {

        every { mockRepository.getHomeData() } returns Single.just(mockResult)

        val test = usecase().test()

        verify { mockRepository.getHomeData() }
        test.assertValue(mockResult)
    }

}