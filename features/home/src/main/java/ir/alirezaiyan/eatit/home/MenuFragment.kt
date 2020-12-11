package ir.alirezaiyan.eatit.home

import android.os.Bundle
import ir.alirezaiyan.views.screen.ContainerFragment
import ir.alirezaiyan.views.screen.simpleController

class MenuFragment : ContainerFragment() {

    companion object{
        fun create(title : String) = MenuFragment().also {
            it.arguments = Bundle().apply {
                putString("title" , title)
            }
        }
    }

    override fun controller() = simpleController {

    }
}
