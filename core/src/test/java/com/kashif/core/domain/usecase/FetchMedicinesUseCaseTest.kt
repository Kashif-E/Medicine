package com.kashif.core.domain.usecase

import com.kashif.core.domain.repository.IMedicineRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FetchMedicinesUseCaseTest {

    private val repository = mockk<IMedicineRepository>(relaxed = true)
    private val useCase = FetchMedicinesUseCase(repository)

    @Test
    fun `invoke should call fetchMedicinesFromNetwork on repository`() = runTest {
        // Act
        useCase()

        // Assert
        coVerify { repository.fetchMedicinesFromNetwork() }
    }
}