package ir.alirezaiyan.eatit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import ir.alirezaiyan.auth.UserManager
import ir.alirezaiyan.navigation.features.HomeNavigation

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0, 0)
        super.onCreate(savedInstanceState)
        when {
            UserManager().newUser -> {
                Handler(Looper.myLooper()!!)
                    .postDelayed({ startActivity(HomeNavigation.home()) },1500)
            }
            else -> startActivity(HomeNavigation.home())
        }
        UserManager().newUser = false
    }

}