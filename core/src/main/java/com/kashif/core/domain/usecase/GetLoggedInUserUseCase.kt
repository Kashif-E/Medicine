package com.kashif.core.domain.usecase

import com.kashif.core.domain.model.User
import com.kashif.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class GetLoggedInUserUseCase(
    private val repository: IUserRepository
) {
    suspend operator fun invoke(): Flow<User?> {
        return repository.getLoggedInUser()
    }
}