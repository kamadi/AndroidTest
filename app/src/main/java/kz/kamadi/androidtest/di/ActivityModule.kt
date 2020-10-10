package kz.kamadi.androidtest.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kz.kamadi.androidtest.presentation.MainActivity
import kz.kamadi.androidtest.presentation.user.UsersFragment

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMain(): MainActivity
}

@Module
abstract class MainModule {

    @ContributesAndroidInjector
    abstract fun mainFragment(): UsersFragment
}