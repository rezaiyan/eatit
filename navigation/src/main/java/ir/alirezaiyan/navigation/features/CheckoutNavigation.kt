package ir.alirezaiyan.navigation.features

import ir.alirezaiyan.navigation.core.Navigator

object CheckoutNavigation : Navigator() {
    fun checkout() = fragmentIntent("ir.alirezaiyan.eatit.checkout.CheckoutFragment")
}
