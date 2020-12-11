package ir.alirezaiyan.views.screen

import android.os.Bundle
import android.view.View
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.mvrx.BaseMvRxFragment
import ir.alirezaiyan.eatit.views.R
import ir.alirezaiyan.views.extensions.enableStateRestoration
import kotlinx.android.synthetic.main.fragment_container.*

abstract class ContainerFragment : BaseMvRxFragment(R.layout.fragment_container) {

    protected val controller by lazy { controller() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        containerRecyclerView.setDelayMsWhenRemovingAdapterOnDetach(0)
        containerRecyclerView.setController(controller)
        containerRecyclerView.enableStateRestoration()
    }

    abstract fun controller(): EpoxyController

    protected inline val recyclerView: EpoxyRecyclerView
        get() = containerRecyclerView

    override fun invalidate() {
        recyclerView.requestModelBuild()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        controller.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        controller.cancelPendingModelBuild()
        super.onDestroyView()
    }
}
