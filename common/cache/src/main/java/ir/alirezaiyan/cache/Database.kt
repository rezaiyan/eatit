package ir.alirezaiyan.cache

import io.reactivex.Single

class Database<T>(private val cache: Cache = Cache()) {

    operator fun plusAssign(it: T) {
        val items = getAll().toMutableList()
        items.add(it)
        cache.set(CART_TABLE, items)
    }

    var items: Single<List<T>>
        get() = cache.load(CART_TABLE)
        set(value) = cache.set(CART_TABLE, value)

    fun getAll() : List<T> = cache.get<List<T>>(CART_TABLE) ?: emptyList()

    internal companion object {
        const val CART_TABLE = "cart"
    }
}