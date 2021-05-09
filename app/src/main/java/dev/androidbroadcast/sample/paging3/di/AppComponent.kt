package dev.androidbroadcast.sample.paging3.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dev.androidbroadcast.sample.paging3.ui.home.HomeActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(homeActivity: HomeActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun create(): AppComponent
    }
}