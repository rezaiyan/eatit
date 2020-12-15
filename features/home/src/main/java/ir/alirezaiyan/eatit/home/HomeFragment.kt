package ir.alirezaiyan.eatit.home

import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ir.alirezaiyan.eatit.domain.home.Category
import ir.alirezaiyan.eatit.resources.*
import ir.alirezaiyan.navigation.extensions.isHome
import ir.alirezaiyan.navigation.extensions.replaceFragment
import ir.alirezaiyan.navigation.features.CartNavigation
import ir.alirezaiyan.views.extensions.appBarHeight
import ir.alirezaiyan.views.extensions.hideWithDelay
import ir.alirezaiyan.views.extensions.isVisible
import ir.alirezaiyan.views.extensions.visible
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : HomeFragmentBase() {

    @Inject
    lateinit var viewModelFactory: HomeViewModel.Factory
    private val viewModel: HomeViewModel by fragmentViewModel(HomeViewModel::class)
    private var tabLayoutMediator: TabLayoutMediator? = null
    private val tabTitles = mutableListOf<String>()

    override fun onStop() {
        super.onStop()
        tabLayoutMediator?.detach()
        bannerViewPager.adapter?.unregisterDataSetObserver(pagerIndicator.dataObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuViewPager.offscreenPageLimit = 3
        Glide.with(this).load(R.drawable.loading)
            .into(loadingImageView)
        appBarLayout.layoutParams =
            CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.MATCH_PARENT,
                appBarHeight()
            )
        fabContainer.animateFab()

        val cardAnim: Drawable? =
            AnimatedVectorDrawableCompat.create(requireContext(), R.drawable.avd_fab_end)
        val basketAnim: Drawable? =
            AnimatedVectorDrawableCompat.create(requireContext(), R.drawable.avd_fab_start)

        parentFragmentManager.addOnBackStackChangedListener {
            if (parentFragmentManager.isHome()) {
                viewModel.loadCart()
                fabIcon.setImageDrawable(cardAnim)
                (cardAnim as Animatable).start()
                itemCartCounter.scaleNow(1F)
            } else {
                fabIcon.setImageDrawable(basketAnim)
                (basketAnim as Animatable).start()
                itemCartCounter.scaleNow(0F)
            }
        }

        fab.setOnClickListener {
            if (parentFragmentManager.isHome()) {
                if (itemCartCounter.text == "0") {
                    Toast.makeText(requireContext(), R.string.empty_cart_error, Toast.LENGTH_SHORT)
                        .show()
                } else {
                    replaceFragment(CartNavigation.cart(), R.id.homeContainer)
                }
            }

        }

    }

    override fun invalidate() {
        withState(viewModel) { state ->
            if (!state.isLoading) {
                loadingLayout.hideWithDelay()
            }
            state.request()?.let {
                if (menuViewPager.adapter == null) {
                    tabTitles.clear()
                    tabTitles.addAll(it.getTitles())
                    menuViewPager.adapter = menuAdapter(it.categories)

                    bannerViewPager.adapter = bannerAdapter(it.getBanners())
                        .also { pagerAdapter ->
                            pagerAdapter.registerDataSetObserver(pagerIndicator.dataObserver)
                        }
                    pagerIndicator.setViewPager(bannerViewPager)

                    tabLayoutMediator =
                        TabLayoutMediator(tabLayout, menuViewPager) { tab, position ->
                            tab.text = tabTitles[position]
                        }.also { it.attach() }
                }

                state.cartItemCount()?.let { count ->
                    itemCartCounter visible (count > 0)
                    if (count == 0) {
                        itemCartCounter.scaleNow(0F)
                    } else if (!itemCartCounter.isVisible()) {
                        itemCartCounter.scaleNow(1F)
                    }
                    itemCartCounter.textWithAnim = count.toString()
                } ?: itemCartCounter.scaleNow(0F)

            }

        }
    }

    private fun menuAdapter(categories: List<Category>): FragmentStateAdapter =
        object :
            FragmentStateAdapter(this) {

            override fun getItemCount(): Int = categories.size

            override fun createFragment(position: Int): Fragment =
                MenuFragment.create(categories[position])
        }

    private fun bannerAdapter(banners: List<String>): PagerAdapter = object :
        PagerAdapter() {

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

}

abstract class HomeFragmentBase : BaseMvRxFragment(R.layout.fragment_home)