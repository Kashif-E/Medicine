package com.kashif.core.domain.usecase

import com.kashif.core.domain.model.User
import com.kashif.core.domain.repository.IUserRepository

class InsertUserUseCase(
    private val repository: IUserRepository
) {
    suspend operator fun invoke(user: User) {
        repository.insertUser(user)
    }
}