package dev.androidbroadcast.sample.paging3

import android.app.Application
import android.content.Context
import dev.androidbroadcast.sample.paging3.di.AppComponent
import dev.androidbroadcast.sample.paging3.di.DaggerAppComponent


class NewsApplication : Application() {

    private var _appComponent: AppComponent? = null

    val appComponent: AppComponent
        get() = checkNotNull(_appComponent)

    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerAppComponent.builder()
            .application(this)
            .create()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is NewsApplication -> appComponent
        else -> (applicationContext as NewsApplication).appComponent
    }