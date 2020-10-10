package kz.kamadi.androidtest.domain.model

import android.accounts.NetworkErrorException
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed class Response<out R> {

    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val exception: Exception) : Response<Nothing>() {

        val isNetworkError = exception is NetworkErrorException
                || exception is SocketTimeoutException
                || exception is UnknownHostException
                || exception is ConnectException
                || exception is IOException

        val isHttpException = exception is HttpException
    }

    data class Loading(val isLoading: Boolean = true) : Response<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is Loading -> "Loading"
        }
    }

    val isSuccessful: Boolean
        get() = this is Response.Success
}