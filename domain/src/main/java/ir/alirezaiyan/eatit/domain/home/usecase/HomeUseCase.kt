package ir.alirezaiyan.eatit.domain.home.usecase

import io.reactivex.Single
import ir.alirezaiyan.eatit.domain.home.repo.HomeRepository
import ir.alirezaiyan.network.model.Home
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val repository: HomeRepository) : () -> Single<Home> {

    override fun invoke(): Single<Home> = repository.getHomeData()

}
