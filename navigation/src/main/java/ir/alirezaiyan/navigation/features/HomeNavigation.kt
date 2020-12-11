package ir.alirezaiyan.navigation.features

import android.content.Intent
import ir.alirezaiyan.navigation.core.Navigator

object HomeNavigation : Navigator() {
    fun home() = fragmentIntent("ir.alirezaiyan.eatit.home.HomeFragment")
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
}
