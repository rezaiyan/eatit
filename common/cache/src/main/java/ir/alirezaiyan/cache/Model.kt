package ir.alirezaiyan.cache

data class FoodDBModel(
    val id: Long,
    val title: String,
    val description: String,
    val metadata: String,
    val price: String,
    val photo: String
)