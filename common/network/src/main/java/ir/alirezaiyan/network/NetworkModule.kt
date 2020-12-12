package ir.alirezaiyan.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.schedulers.Schedulers
import ir.alirezaiyan.eatit.network.BuildConfig
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    internal fun providesRetrofit(): Retrofit =
        createNetworkClient("https://google.com")
            .build()

    @Provides
    @Singleton
    internal fun provideService(retrofit: Retrofit): EatItService =
        retrofit.create(EatItService::class.java)

}

fun createNetworkClient(baseUrl: String, debug: Boolean = BuildConfig.DEBUG): Retrofit.Builder =
    retrofitClient(baseUrl, httpClient(debug))

private fun httpClient(debug: Boolean): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
    val clientBuilder = OkHttpClient.Builder()
    if (debug) {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(httpLoggingInterceptor)
    }

    clientBuilder.addInterceptor(fakeInterceptor())
    return clientBuilder.build()
}

fun fakeInterceptor() = object : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        return Response.Builder()
            .code(200)
            .message("")
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(FAKE_RESPONSE.toResponseBody("application/json".toMediaTypeOrNull()))
            .build()
    }

}

private fun retrofitClient(baseUrl: String, httpClient: OkHttpClient): Retrofit.Builder =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
