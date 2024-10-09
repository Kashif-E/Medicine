package com.kashif.core.data.respository

import com.kashif.core.data.local.UserDao
import com.kashif.core.domain.model.User
import com.kashif.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val userDao: UserDao
) : IUserRepository {
    override suspend fun insertUser(user: User) {
        withContext(kotlinx.coroutines.Dispatchers.IO) {
            userDao.insertUser(user)
        }
    }

    override suspend fun getLoggedInUser(): Flow<User?> {
           return userDao.getLoggedInUser()

    }
}