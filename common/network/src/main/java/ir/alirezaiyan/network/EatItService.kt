package ir.alirezaiyan.network

import io.reactivex.Single
import retrofit2.http.GET

interface EatItService {

    @GET("home")
    fun getHome(): Single<HomeResponse>

}