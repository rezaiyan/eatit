package ir.alirezaiyan.eatit.domain.home.mapper

import ir.alirezaiyan.cache.FoodDBModel
import ir.alirezaiyan.eatit.domain.home.*
import ir.alirezaiyan.eatit.domain.mapper.Mapper
import ir.alirezaiyan.eatit.domain.mapper.listMap
import ir.alirezaiyan.network.*

class HomeMapper : Mapper<HomeResponse, Home> {
    override fun map(item: HomeResponse) = Home(
        PromotionMapper().listMap(item.promotions),
        CategoryMapper().listMap(item.categories)
    )

}

class PromotionMapper : Mapper<PromotionResponse, Promotion> {
    override fun map(item: PromotionResponse) = Promotion(item.id, item.photo, item.action)

}

class CategoryMapper : Mapper<CategoryResponse, Category> {
    override fun map(item: CategoryResponse) =
        Category(item.title, FilterMapper().listMap(item.filters), FoodMapper().listMap(item.foods))

}

class FilterMapper : Mapper<FilterResponse, Filter> {
    override fun map(item: FilterResponse) = Filter(item.id, item.title)

}

class FoodMapper : Mapper<FoodResponse, Food> {
    override fun map(item: FoodResponse) =
        Food(item.id, item.title, item.description, item.metadata, item.price, item.photo)
}

class FoodToDBMapper : Mapper<Food, FoodDBModel> {
    override fun map(item: Food) =
        FoodDBModel(item.id, item.title, item.description, item.metadata, item.price, item.photo)
}

class FoodToDomainMapper : Mapper<FoodDBModel, Food> {
    override fun map(item: FoodDBModel) =
        Food(item.id, item.title, item.description, item.metadata, item.price, item.photo)
}