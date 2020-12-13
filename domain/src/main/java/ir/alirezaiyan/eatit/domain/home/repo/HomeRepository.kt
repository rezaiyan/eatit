package ir.alirezaiyan.eatit.domain.home.repo

import io.reactivex.Single
import ir.alirezaiyan.cache.FoodDBModel
import ir.alirezaiyan.eatit.domain.home.datasource.HomeDataSource
import ir.alirezaiyan.eatit.domain.home.Food
import ir.alirezaiyan.eatit.domain.home.Home
import ir.alirezaiyan.eatit.domain.home.mapper.HomeMapper
import ir.alirezaiyan.eatit.domain.mapper.Mapper
import ir.alirezaiyan.eatit.domain.mapper.listMap
import ir.alirezaiyan.network.HomeResponse
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val homeDataSource: HomeDataSource,
    private val homeMapper: Mapper<HomeResponse, Home>,
    private val foodDBMapper: Mapper<Food, FoodDBModel>,
    private val foodUIMapper: Mapper<FoodDBModel, Food>
) {

    fun getHomeData(): Single<Home> = homeDataSource.getHomeData().map { homeMapper.map(it) }

    fun getCartItems() = homeDataSource.getCartItems().map {
        foodUIMapper.listMap(it)
    }

    fun addToCart(item: Food) =
        homeDataSource.addToCart(foodDBMapper.map(item))
            .map {
                foodUIMapper.listMap(it)
            }
}
