package com.kashif.core.domain.usecase

import com.kashif.core.domain.model.User
import com.kashif.core.domain.repository.IUserRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetLoggedInUserUseCaseTest {

    private val repository = mockk<IUserRepository>()
    private val useCase = GetLoggedInUserUseCase(repository)

    @Test
    fun `invoke should return user from repository`() = runTest {
        // Arrange
        val user = User(email = "test@example.com")
        every { repository.getLoggedInUser() } returns flowOf(user)

        // Act
        val result = useCase().first()

        // Assert
        assertEquals(user, result)
    }
}