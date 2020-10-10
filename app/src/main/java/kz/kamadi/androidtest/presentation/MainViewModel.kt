package kz.kamadi.androidtest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kz.kamadi.androidtest.di.viewmodel.ContextProvider
import kz.kamadi.androidtest.domain.model.Response
import kz.kamadi.androidtest.domain.model.User
import kz.kamadi.androidtest.domain.repository.UserRepository
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainViewModel @Inject constructor(
    private val repository: UserRepository,
    private val contextProvider: ContextProvider
) : ViewModel(), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = contextProvider.ui + job

    private val _loading = MutableLiveData<Boolean>()

    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<Response.Error?>()

    val error: LiveData<Response.Error?>
        get() = _error

    private val _users = MutableLiveData<List<User>>()

    val users: LiveData<List<User>>
        get() = _users

    fun getUsers(forceUpdate: Boolean = false) {
        if (forceUpdate || _users.value.isNullOrEmpty()) {
            _loading.value = true
            launch {
                val response = repository.getUsers(since = 0)
                _loading.value = false
                if (response is Response.Success) {
                    _users.value = response.data
                } else {
                    _error.value = response as Response.Error
                    _error.value = null
                }
            }
        }
    }

    fun getNextUsers() {
        val items = _users.value?.toMutableList()
        if (!items.isNullOrEmpty()) {
            _loading.value = true
            launch {
                val response = repository.getUsers(since = items.last().id)
                _loading.value = false
                if (response is Response.Success) {
                    items.addAll(response.data)
                    _users.value = items
                } else {
                    _error.value = response as Response.Error
                    _error.value = null
                }
            }
        }
    }
}