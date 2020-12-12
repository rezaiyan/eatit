package ir.alirezaiyan.eatit.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_banner.*

class BannerFragment : Fragment(R.layout.fragment_banner){

    companion object{
        fun create(url : String) = BannerFragment().also {
            it.arguments = Bundle().apply {
                putString("url" , url)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this).load(arguments!!.getString("url"))
            .into(bannerImageView)
    }
}