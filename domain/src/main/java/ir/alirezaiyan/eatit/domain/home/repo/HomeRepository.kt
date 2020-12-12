package ir.alirezaiyan.eatit.domain.home.repo

import io.reactivex.Single
import ir.alirezaiyan.network.datasource.HomeDataSource
import ir.alirezaiyan.network.model.Home
import javax.inject.Inject

class HomeRepository @Inject constructor(private val homeDataSource: HomeDataSource) {

    fun getHomeData(): Single<Home> =
        homeDataSource.getHomeData()
}
