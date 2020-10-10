package kz.kamadi.androidtest

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kz.kamadi.androidtest.di.ApplicationComponent
import kz.kamadi.androidtest.di.DaggerApplicationComponent
import javax.inject.Inject

class App : Application() , HasAndroidInjector {

    private lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        initApplicationComponent()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    private fun initApplicationComponent() {
        this.applicationComponent = DaggerApplicationComponent.builder()
            .application(this)
            .build()

        applicationComponent.inject(this)
    }
}