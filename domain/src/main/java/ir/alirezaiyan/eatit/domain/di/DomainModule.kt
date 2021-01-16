package ir.alirezaiyan.eatit.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.alirezaiyan.eatit.cache.FoodDBModel
import ir.alirezaiyan.eatit.domain.home.Food
import ir.alirezaiyan.eatit.domain.home.Home
import ir.alirezaiyan.eatit.domain.home.mapper.FoodToDBMapper
import ir.alirezaiyan.eatit.domain.home.mapper.FoodToDomainMapper
import ir.alirezaiyan.eatit.domain.home.mapper.HomeMapper
import ir.alirezaiyan.eatit.domain.mapper.Mapper
import ir.alirezaiyan.network.HomeResponse

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideHomeMapper(): Mapper<HomeResponse, Home> = HomeMapper()

    @Provides
    fun provideFoodDBMapper(): Mapper<Food, FoodDBModel> = FoodToDBMapper()

    @Provides
    fun provideFoodDomainMapper(): Mapper<FoodDBModel, Food> = FoodToDomainMapper()

}