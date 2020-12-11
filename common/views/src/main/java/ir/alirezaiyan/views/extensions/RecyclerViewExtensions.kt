package ir.alirezaiyan.views.extensions

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.enableStateRestoration() {
    adapter!!.stateRestorationPolicy =
        RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
}