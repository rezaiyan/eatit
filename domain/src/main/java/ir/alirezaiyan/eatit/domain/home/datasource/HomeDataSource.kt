package ir.alirezaiyan.eatit.domain.home.datasource

import io.reactivex.Single
import ir.alirezaiyan.eatit.cache.Database
import ir.alirezaiyan.eatit.cache.FoodDBModel
import ir.alirezaiyan.network.EatItService
import ir.alirezaiyan.network.HomeResponse
import javax.inject.Inject

class HomeDataSource @Inject constructor(
    private val service: EatItService,
    private val database: Database<FoodDBModel>
) {

    fun getHomeData(): Single<HomeResponse> = service.getHome()
    fun getCartItems(): Single<List<FoodDBModel>> = database.items
    fun addToCart(item: FoodDBModel): Single<List<FoodDBModel>> =
        if (database.getAll().isNotEmpty()){
        database.items.map {
            database += item
            database.getAll()
        }
    }else {
        database += item
        database.items
    }

    fun removeFromCart(item: FoodDBModel): Single<List<FoodDBModel>> =
        if (database.getAll().isNotEmpty()){
        database.items.map {
            database -= item
            database.getAll()
        }
    }else {
        Single.just(emptyList())
    }

}