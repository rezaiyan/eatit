package ir.alirezaiyan.eatit.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*

@Suppress("Unused")
class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabs = listOf("Pizza", "Sushi", "Drinks")
        menuViewPager.adapter = stateAdapter(tabs)
        TabLayoutMediator(tabLayout, menuViewPager) { tab, position ->
            tab.text = tabs[position]
        }.attach()
        menuViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
            }
        })
    }

    private fun stateAdapter(tabs : List<String>): FragmentStateAdapter = object :
        FragmentStateAdapter(this) {

        override fun getItemCount(): Int = tabs.size

        override fun createFragment(position: Int): Fragment = MenuFragment.create(tabs[position])
    }

}
