package kz.kamadi.androidtest.data.network

import kz.kamadi.androidtest.domain.model.User
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("users")
    suspend fun getUsers(@Query("since") since: Long): List<User>
}