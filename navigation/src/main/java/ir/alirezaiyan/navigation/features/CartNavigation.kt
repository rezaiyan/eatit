package ir.alirezaiyan.navigation.features

import ir.alirezaiyan.navigation.core.Navigator

object CartNavigation : Navigator() {
    const val name = "ir.alirezaiyan.eatit.cart.HostFragment"
    fun cart() = fragment(name)
}
