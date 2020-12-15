package ir.alirezaiyan.eatit.cart.page

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import ir.alirezaiyan.eatit.cart.R
import kotlinx.android.synthetic.main.fragment_empty.*

class EmptyFragment : Fragment(R.layout.fragment_empty) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this).load(R.drawable.empty_view)
            .into(emptyImageView)
    }

}