package ir.alirezaiyan.navigation.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import ir.alirezaiyan.navigation.R

const val PACKAGE_NAME = "ir.alirezaiyan.eatit"

fun Fragment.replaceFragment(
    fragment: Fragment,
    containerId: Int
) = parentFragmentManager
    .beginTransaction()
    .setCustomAnimations(
        R.anim.enter_from_bottom,
        R.anim.exit_to_bottom,
        R.anim.enter_from_bottom,
        R.anim.exit_to_bottom
    )
    .replace(containerId, fragment)
    .also { it.addToBackStack(null) }
    .commit()

fun FragmentActivity.replaceFragment(
    fragment: Fragment,
    backStack: Boolean = true,
    containerId: Int = android.R.id.content
) = supportFragmentManager
    .beginTransaction()
    .setCustomAnimations(
        R.anim.enter_from_bottom,
        R.anim.exit_to_bottom,
        R.anim.enter_from_bottom,
        R.anim.exit_to_bottom
    )
    .add(containerId, fragment)
    .also { if (backStack) it.addToBackStack(null) }
    .commit()

fun FragmentManager.isHome(): Boolean {
    val myFragments = fragments
        .filter { it.javaClass.name.contains(PACKAGE_NAME) }
    return myFragments[myFragments.size - 1].javaClass.name == "${PACKAGE_NAME}.home.HomeFragment"
}
