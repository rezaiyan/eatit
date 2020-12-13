package ir.alirezaiyan.network

data class HomeResponse(
    val promotions: List<PromotionResponse>,
    val categories: List<CategoryResponse>
)

data class PromotionResponse(val id: Long, val photo: String, val action: String)

data class CategoryResponse(
    val title: String,
    val filters: List<FilterResponse>,
    val foods: List<FoodResponse>
)

data class FilterResponse(val id: Long, val title: String)

data class FoodResponse(
    val id: Long,
    val title: String,
    val description: String,
    val metadata: String,
    val price: String,
    val photo: String
)