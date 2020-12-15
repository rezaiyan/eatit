package ir.alirezaiyan.eatit.cart

import android.os.Bundle
import android.view.View
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.airbnb.mvrx.BaseMvRxFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ir.alirezaiyan.eatit.cart.page.CartFragment
import ir.alirezaiyan.eatit.cart.page.EmptyFragment
import kotlinx.android.synthetic.main.fragment_host.*
import javax.inject.Inject

@AndroidEntryPoint
class HostFragment : HostFragmentBase() {

    @Inject
    lateinit var viewModelFactory: CartViewModel.Factory
    private var tabLayoutMediator: TabLayoutMediator? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        iconImageView.setOnClickListener { parentFragmentManager.popBackStack() }

        val tabTitles = listOf("Cart", "Orders", "Information")
        viewPager.adapter = pagerAdapter(tabTitles)
        tabLayoutMediator =
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = tabTitles[position]
            }.also { it.attach() }
    }

    override fun invalidate() {
    }

    private fun pagerAdapter(tabTitles: List<String>): FragmentStateAdapter =
        object : FragmentStateAdapter(this) {

            override fun getItemCount(): Int = tabTitles.size

            override fun createFragment(position: Int) = when (position) {
                CART_POSITION -> CartFragment()
                else -> EmptyFragment()
            }
        }
}

abstract class HostFragmentBase : BaseMvRxFragment(R.layout.fragment_host)
private const val CART_POSITION = 0