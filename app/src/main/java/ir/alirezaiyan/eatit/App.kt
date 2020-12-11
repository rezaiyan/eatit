package ir.alirezaiyan.eatit

import android.app.Application
import com.airbnb.mvrx.MvRx
import com.airbnb.mvrx.MvRxViewModelConfigFactory
import ir.alirezaiyan.cache.CacheLibrary
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks())
        MvRx.viewModelConfigFactory = MvRxViewModelConfigFactory(applicationContext)
        CacheLibrary.init(this)
    }
}
