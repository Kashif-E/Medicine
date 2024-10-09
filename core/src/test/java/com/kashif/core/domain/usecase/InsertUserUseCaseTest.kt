package com.kashif.core.domain.usecase


import com.kashif.core.domain.model.User
import com.kashif.core.domain.repository.IUserRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class InsertUserUseCaseTest {

    private val repository = mockk<IUserRepository>(relaxed = true)
    private val useCase = InsertUserUseCase(repository)

    @Test
    fun `invoke should call insertUser on repository`() = runTest {
        // Arrange
        val user = User(email = "test@example.com")

        // Act
        useCase(user)

        // Assert
        coVerify { repository.insertUser(user) }
    }
}