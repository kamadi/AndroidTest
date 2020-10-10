package kz.kamadi.androidtest.di.viewmodel

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class ContextProviderImpl @Inject constructor() : ContextProvider {

    override val ui: CoroutineContext
        get() = Dispatchers.Main
}

interface ContextProvider {
    val ui: CoroutineContext
}