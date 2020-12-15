package ir.alirezaiyan.navigation.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ir.alirezaiyan.navigation.extensions.isHome
import ir.alirezaiyan.navigation.extensions.replaceFragment
import ir.alirezaiyan.navigation.features.HomeNavigation

@AndroidEntryPoint
class ContainerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0, 0)
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null)
            loadFragment(HomeNavigation.name)
    }

    private fun loadFragment(fragmentName: String = intent.getStringExtra(FRAGMENT_NAME)!!) {
        replaceFragment(
            fragment = ClassRegistry.loadFragment(
                fragmentName,
                intent.getBundleExtra(FRAGMENT_BUNDLE)
            ),
            backStack = false
        )
    }

    override fun onBackPressed() {
        if (supportFragmentManager.isHome()) {
            overridePendingTransition(0, 0)
            finishAfterTransition()
        } else {
            super.onBackPressed()
        }

    }

    companion object {
        const val FRAGMENT_NAME = "fragment_name"
        const val FRAGMENT_BUNDLE = "fragment_bundle"
    }
}
