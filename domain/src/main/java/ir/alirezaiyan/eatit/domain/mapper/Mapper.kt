package ir.alirezaiyan.eatit.domain.mapper

import io.reactivex.Observable


interface Mapper<T, R> {
    fun map(item: T): R
}

fun <T, R> Mapper<T, R>.listMap(items: List<T>): List<R> =
    items.map { this.map(it) }

fun <T, R> Mapper<T, R>.listMapNullable(items: List<T?>): List<R?> =
    items.map {
        if (it != null) {
            this.map(it)
        }else{
            null
        }
    }

fun <T, R> Observable<T>.mapWith(mapper: Mapper<T, R>): Observable<R> =
    map {
        mapper.map(it)
    }

fun <T, R> Observable<T?>.mapWithNullable(mapper: Mapper<T, R>): Observable<R?> =
    map {
        if (it != null) {
            mapper.map(it)
        } else {
            null
        }
    }

fun <T, R> Observable<List<T>>.mapListWith(mapper: Mapper<T, R>): Observable<List<R>> =
    map {
        mapper.listMap(it)
    }

fun <T, R> Observable<List<T?>>.mapListWithNullable(mapper: Mapper<T, R>): Observable<List<R?>> =
    map {
        mapper.listMapNullable(it)
    }