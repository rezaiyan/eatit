package ir.alirezaiyan.network.datasource

import io.reactivex.Single
import ir.alirezaiyan.network.EatItService
import ir.alirezaiyan.network.model.Home
import javax.inject.Inject

class HomeDataSource @Inject constructor(private val service: EatItService) {

    fun getHomeData(): Single<Home> = service.getHome()

}