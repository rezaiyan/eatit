package ir.alirezaiyan.navigation.features

import ir.alirezaiyan.navigation.core.Navigator

object BasketNavigation : Navigator() {
    fun basket() = fragment("ir.alirezaiyan.eatit.basket.BasketFragment")
}
