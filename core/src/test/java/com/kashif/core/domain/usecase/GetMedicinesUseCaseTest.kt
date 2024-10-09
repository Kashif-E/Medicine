package com.kashif.core.domain.usecase


import com.kashif.core.domain.model.Medicine
import com.kashif.core.domain.repository.IMedicineRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetMedicinesUseCaseTest {

    private val repository = mockk<IMedicineRepository>()
    private val useCase = GetMedicinesUseCase(repository)

    @Test
    fun `invoke should return flow of medicines from repository`() = runTest {
        // Arrange
        val medicines = listOf(
            Medicine(id = "1", name = "aspirin", dose = "", strength = "500 mg")
        )
        every { repository.getAllMedicines() } returns flowOf(medicines)

        // Act
        val result = useCase().first()

        // Assert
        assertEquals(medicines, result)
    }
}