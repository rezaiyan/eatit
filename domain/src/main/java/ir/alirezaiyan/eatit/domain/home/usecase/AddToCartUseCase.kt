package ir.alirezaiyan.eatit.domain.home.usecase

import io.reactivex.Single
import ir.alirezaiyan.eatit.domain.home.repo.HomeRepository
import ir.alirezaiyan.eatit.domain.home.Food
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(private val repository: HomeRepository) :
        (Food) -> Single<List<Food>> {

    override fun invoke(item: Food) : Single<List<Food>> = repository.addToCart(item)

}
