package com.kashif.core.domain.repository

import com.kashif.core.domain.model.User
import kotlinx.coroutines.flow.Flow


interface IUserRepository {
    suspend fun insertUser(user: User)
    suspend fun getLoggedInUser(): Flow<User?>
}