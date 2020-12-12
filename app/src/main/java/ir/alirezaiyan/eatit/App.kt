package ir.alirezaiyan.eatit

import android.app.Application
import com.airbnb.mvrx.MvRx
import com.airbnb.mvrx.MvRxViewModelConfigFactory
import dagger.hilt.android.HiltAndroidApp
import ir.alirezaiyan.cache.CacheLibrary
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks())
        MvRx.viewModelConfigFactory = MvRxViewModelConfigFactory(applicationContext)
        CacheLibrary.init(this)
    }
}
