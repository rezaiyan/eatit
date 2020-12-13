package ir.alirezaiyan.eatit.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ir.alirezaiyan.network.model.Category
import ir.alirezaiyan.views.extensions.appBarHeight
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : HomeFragmentBase() {

    @Inject
    lateinit var viewModelFactory: HomeViewModel.Factory
    private val viewModel: HomeViewModel by fragmentViewModel(HomeViewModel::class)
    private var tabLayoutMediator : TabLayoutMediator? = null
    private val tabTitles = mutableListOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appBarLayout.layoutParams =
            CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.MATCH_PARENT,
                appBarHeight()
            )

        swipeRefreshLayout.setOnRefreshListener { viewModel.onSwipeAction() }
        menuViewPager.offscreenPageLimit = 3
        menuViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
            }
        })

        viewModel.asyncSubscribe(HomeState::request, uniqueOnly(), onSuccess = {
            tabTitles.clear()
            tabTitles.addAll(it.getTitles())
            menuViewPager.adapter = menuAdapter(tabTitles, it.categories)
            bannerViewPager.adapter = bannerAdapter(it.getBanners())
            pagerIndicator.setViewPager(bannerViewPager)
            bannerViewPager.adapter!!.registerDataSetObserver(pagerIndicator.dataObserver)
            tabLayoutMediator = TabLayoutMediator(tabLayout, menuViewPager) { tab, position ->
                tab.text = tabTitles[position]
            }.also { it.attach() }

        })

    }

    override fun invalidate() {
        withState(viewModel) { state ->
            swipeRefreshLayout.isRefreshing = state.isLoading
        }
    }

    private fun menuAdapter(tabs: List<String>, categories: List<Category>): FragmentStateAdapter = object :
        FragmentStateAdapter(this) {

        override fun getItemCount(): Int = tabs.size

        override fun createFragment(position: Int): Fragment = MenuFragment.create(categories[position])
    }

    private fun bannerAdapter(banners: List<String>): PagerAdapter = object :
        PagerAdapter(){

        override fun getCount(): Int {
            return banners.size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view: View = LayoutInflater.from(container.context)
                .inflate(R.layout.fragment_banner, container, false)
            val bannerImageView = view.findViewById<ImageView>(R.id.bannerImageView)
            Glide.with(container.context).load(banners[position])
                .into(bannerImageView)
            container.addView(view)
            return view
        }
        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

    }

    override fun onStop() {
        super.onStop()
        tabLayoutMediator?.detach()
        if (bannerViewPager.adapter != null && pagerIndicator != null) {
            bannerViewPager.adapter!!.unregisterDataSetObserver(pagerIndicator.dataObserver)
        }
    }
}
abstract class HomeFragmentBase : BaseMvRxFragment(R.layout.fragment_home)