package com.kashif.core.data.respository

import com.kashif.core.data.local.UserDao
import com.kashif.core.domain.model.User
import com.kashif.core.domain.repository.IUserRepository
import io.mockk.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class UserRepositoryImplTest {

    private val userDao = mockk<UserDao>(relaxed = true)
    private val repository: IUserRepository = UserRepositoryImpl(userDao)

    @Test
    fun `insertUser should call dao insertUser`() = runTest {
        // Arrange
        val user = User(email = "test@example.com")

        // Act
        repository.insertUser(user)

        // Assert
        coVerify { userDao.insertUser(user) }
    }

    @Test
    fun `getLoggedInUser should return user from dao`() = runTest {
        // Arrange
        val user = User(email = "test@example.com")
        every { userDao.getLoggedInUser() } returns flowOf(user)

        // Act
        val result = repository.getLoggedInUser().first()

        // Assert
        assert(result == user)
    }
}