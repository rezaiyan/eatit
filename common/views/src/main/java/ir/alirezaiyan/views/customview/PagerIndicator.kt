package ir.alirezaiyan.views.customview

import android.annotation.SuppressLint
import android.content.Context
import android.database.DataSetObserver
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import ir.alirezaiyan.eatit.resources.scale
import ir.alirezaiyan.eatit.views.R
import ir.alirezaiyan.views.extensions.dpToPx

class PagerIndicator : LinearLayout {

    private var viewPager: ViewPager? = null
    private var indicatorWidth = -1
    private var indicatorHeight = -1
    private var indicatorMargin = -1
    private var indicatorBackgroundResId = R.drawable.ic_dot
    private var lastPosition = -1
    private val internalPageChangeListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            if (viewPager!!.adapter == null || viewPager!!.adapter!!.count <= 0) {
                return
            }
            if (lastPosition >= 0) {
                getChildAt(lastPosition).scale(1f)
            }
            getChildAt(position).scale(1.5f)
            lastPosition = position
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }
    val dataObserver: DataSetObserver = object : DataSetObserver() {
        override fun onChanged() {
            super.onChanged()
            val newCount = viewPager!!.adapter!!.count
            if (newCount == childCount) {
                return
            }
            lastPosition = if (lastPosition < newCount) {
                viewPager!!.currentItem
            } else {
                -1
            }
        }

    }

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    fun setViewPager(viewPager: ViewPager?) {
        this.viewPager = viewPager
        if (this.viewPager!!.adapter != null) {
            lastPosition = -1
            createIndicators()
            this.viewPager!!.removeOnPageChangeListener(internalPageChangeListener)
            this.viewPager!!.addOnPageChangeListener(internalPageChangeListener)
            internalPageChangeListener.onPageSelected(this.viewPager!!.currentItem)
        }
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        handleTypedArray(context, attrs)
        checkIndicatorConfig()
    }

    private fun handleTypedArray(context: Context, attrs: AttributeSet?) {
        if (attrs == null) return
        @SuppressLint("CustomViewStyleable") val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.DotsIndicator)
        indicatorWidth = typedArray.getDimensionPixelSize(R.styleable.DotsIndicator_dot_width, -1)
        indicatorHeight =
            typedArray.getDimensionPixelSize(R.styleable.DotsIndicator_dot_height, -1)
        indicatorMargin =
            typedArray.getDimensionPixelSize(R.styleable.DotsIndicator_dot_margin, -1)
        indicatorBackgroundResId = typedArray.getResourceId(
            R.styleable.DotsIndicator_dot_drawable,
            R.drawable.ic_dot
        )
        val orientation = typedArray.getInt(R.styleable.DotsIndicator_dot_orientation, -1)
        setOrientation(if (orientation == VERTICAL) VERTICAL else HORIZONTAL)
        val gravity = typedArray.getInt(R.styleable.DotsIndicator_dot_gravity, -1)
        setGravity(if (gravity >= 0) gravity else Gravity.CENTER)
        typedArray.recycle()
    }

    private fun checkIndicatorConfig() {
        if (indicatorWidth < 0) {
            indicatorWidth = dpToPx(DEFAULT_INDICATOR_WIDTH).toInt()
        }
        if (indicatorHeight < 0) {
            indicatorHeight = dpToPx(DEFAULT_INDICATOR_WIDTH).toInt()
        }
        if (indicatorMargin < 0) {
            indicatorMargin = dpToPx(DEFAULT_INDICATOR_WIDTH).toInt()
        }
        if (indicatorBackgroundResId == 0) {
            indicatorBackgroundResId = R.drawable.ic_dot
        }
    }

    private fun createIndicators() {
        removeAllViews()
        val count = viewPager!!.adapter!!.count
        if (count <= 0) {
            return
        }
        for (i in 0 until count) {
            addIndicator(indicatorBackgroundResId)
        }
    }

    private fun addIndicator(@DrawableRes backgroundDrawableId: Int) {
        val indicator = View(context)
        indicator.setBackgroundResource(backgroundDrawableId)
        addView(indicator, indicatorWidth, indicatorHeight)
        indicator.layoutParams = (indicator.layoutParams as LayoutParams).apply {
            leftMargin = indicatorMargin
            rightMargin = indicatorMargin
            topMargin = indicatorMargin
            bottomMargin = indicatorMargin
        }
    }

    companion object {
        private const val DEFAULT_INDICATOR_WIDTH = 5f
    }
}