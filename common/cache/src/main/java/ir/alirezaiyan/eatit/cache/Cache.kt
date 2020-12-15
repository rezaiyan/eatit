package ir.alirezaiyan.eatit.cache

import com.pacoworks.rxpaper2.RxPaperBook
import io.paperdb.Paper
import io.reactivex.Single

class Cache {

    fun <T> load(key: String): Single<T> = RxPaperBook.with().read(key)

    fun <T> get(key: String): T? = Paper.book().read(key)

    fun <T> set(key: String, data: T) = Paper.book().write(key, data).let { Unit }
}
