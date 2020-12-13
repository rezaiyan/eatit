package ir.alirezaiyan.navigation.features

import ir.alirezaiyan.navigation.core.Navigator

object CartNavigation : Navigator() {
    fun cart() = fragment("ir.alirezaiyan.eatit.basket.CartFragment")
}
