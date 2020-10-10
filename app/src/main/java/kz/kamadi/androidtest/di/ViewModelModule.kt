package kz.kamadi.androidtest.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kz.kamadi.androidtest.di.viewmodel.ViewModelFactory
import kz.kamadi.androidtest.di.viewmodel.ViewModelKey
import kz.kamadi.androidtest.presentation.MainViewModel

@Module(includes = [MainViewModelModule::class])
object ViewModelModule {

    @Provides
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory{
        return factory
    }
}

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMain(viewModel: MainViewModel): ViewModel
}