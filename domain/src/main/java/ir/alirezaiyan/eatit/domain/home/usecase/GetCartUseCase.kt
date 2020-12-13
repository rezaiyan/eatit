package ir.alirezaiyan.eatit.domain.home.usecase

import io.reactivex.Single
import ir.alirezaiyan.eatit.domain.home.repo.HomeRepository
import ir.alirezaiyan.eatit.domain.home.Food
import javax.inject.Inject

class GetCartUseCase @Inject constructor(private val repository: HomeRepository) :
        () -> Single<List<Food>> {

    override fun invoke() : Single<List<Food>> = repository.getCartItems()

}
