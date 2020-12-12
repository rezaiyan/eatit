package ir.alirezaiyan.network.model

data class Home(val promotions: List<Promotion>, val categories: List<Category>) {

    fun getTitles() = categories.map { it.title }
    fun getBanners() = promotions.map { it.photo }

}

data class Category(val title: String, val filters: List<Filter>, val foods: List<Food>)

data class Promotion(val id: Long, val photo: String, val action: String)

data class Filter(val id: Long, val title: String)

data class Food(
    val id: Long,
    val title: String,
    val description: String,
    val metadata: String,
    val price: String,
    val photo: String
)