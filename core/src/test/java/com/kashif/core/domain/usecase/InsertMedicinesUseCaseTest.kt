package com.kashif.core.domain.usecase

import com.kashif.core.domain.model.Medicine
import com.kashif.core.domain.repository.IMedicineRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class InsertMedicinesUseCaseTest {

    private val repository = mockk<IMedicineRepository>(relaxed = true)
    private val useCase = InsertMedicinesUseCase(repository)

    @Test
    fun `invoke should call insertMedicines on repository`() = runTest {
        // Arrange
        val medicines = listOf(
            Medicine(id = "1", name = "aspirin", dose = "", strength = "500 mg")
        )

        // Act
        useCase(medicines)

        // Assert
        coVerify { repository.insertMedicines(medicines) }
    }
}