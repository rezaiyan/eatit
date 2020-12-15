package ir.alirezaiyan.navigation.features

import android.content.Intent
import ir.alirezaiyan.navigation.core.Navigator

object HomeNavigation : Navigator() {
    const val name = "ir.alirezaiyan.eatit.home.HomeFragment"
    fun home() = fragmentIntent(name)
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
}
