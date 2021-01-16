package ir.alirezaiyan.eatit.cache.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.alirezaiyan.eatit.cache.Database
import ir.alirezaiyan.eatit.cache.FoodDBModel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(): Database<FoodDBModel> = Database()

}