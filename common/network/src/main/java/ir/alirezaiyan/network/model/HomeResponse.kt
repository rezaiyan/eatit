package ir.alirezaiyan.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Home(val promotions: List<Promotion>, val categories: List<Category>) {

    fun getTitles() = categories.map { it.title }
    fun getBanners() = promotions.map { it.photo }

}

data class Promotion(val id: Long, val photo: String, val action: String)

@Parcelize
data class Category(val title: String, val filters: List<Filter>, val foods: List<Food>) :
    Parcelable

@Parcelize
data class Filter(val id: Long, val title: String) : Parcelable

@Parcelize
data class Food(
    val id: Long,
    val title: String,
    val description: String,
    val metadata: String,
    val price: String,
    val photo: String
) : Parcelable