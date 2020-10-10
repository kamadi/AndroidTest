package kz.kamadi.androidtest.data.repository

import kz.kamadi.androidtest.data.network.UserApi
import kz.kamadi.androidtest.domain.model.Response
import kz.kamadi.androidtest.domain.model.User
import kz.kamadi.androidtest.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UserApi
) : UserRepository {

    override suspend fun getUsers(since: Long): Response<List<User>> {
        return try {
            Response.Success(api.getUsers(since = since))
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}