package ir.alirezaiyan.auth

import ir.alirezaiyan.cache.Cache


class UserManager(private val cache: Cache = Cache()) {

    var newUser: Boolean
        get() = cache.get<Boolean>(NEW_USER) ?: true
        set(value) = cache.set(NEW_USER, value)

    internal companion object {
        const val NEW_USER = "new_user"
    }
}
