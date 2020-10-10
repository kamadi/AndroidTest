package kz.kamadi.androidtest.domain.repository

import kz.kamadi.androidtest.domain.model.Response
import kz.kamadi.androidtest.domain.model.User

interface UserRepository {

    suspend fun getUsers(since: Long): Response<List<User>>
}