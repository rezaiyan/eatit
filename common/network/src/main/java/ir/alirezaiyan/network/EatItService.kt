package ir.alirezaiyan.network

import io.reactivex.Single
import ir.alirezaiyan.network.model.Home
import retrofit2.http.GET

interface EatItService {

    @GET("home")
    fun getHome(): Single<Home>

}