package com.kashif.core.data.respository


import com.kashif.core.data.local.MedicineDao
import com.kashif.core.data.remote.MedicineApi
import com.kashif.core.domain.model.AssociatedDrug
import com.kashif.core.domain.model.DataItem
import com.kashif.core.domain.model.Diabetes
import com.kashif.core.domain.model.Medication
import com.kashif.core.domain.model.MedicationsClass
import com.kashif.core.domain.model.Medicine
import com.kashif.core.domain.model.MedicineResponse
import com.kashif.core.domain.model.Problem
import com.kashif.core.domain.repository.IMedicineRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class MedicineRepositoryImplTest {

    private lateinit var repository: IMedicineRepository
    private val dao = mockk<MedicineDao>(relaxed = true)
    private val api = mockk<MedicineApi>()
    private val ioDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        repository = MedicineRepositoryImpl(dao, api, ioDispatcher)
    }

    @Test
    fun `insertMedicines should call dao insertMedicines`() = runTest {
        // Arrange
        val medicines = listOf(
            Medicine(id = "1", name = "aspirin", dose = "", strength = "500 mg")
        )

        // Act
        repository.insertMedicines(medicines)

        // Assert
        coVerify { dao.insertMedicines(medicines) }
    }

    @Test
    fun `getAllMedicines should return flow from dao`() = runTest {
        // Arrange
        val medicines = listOf(
            Medicine(id = "1", name = "aspirin", dose = "", strength = "500 mg")
        )
        every { dao.getAllMedicines() } returns flowOf(medicines)

        // Act
        val result = repository.getAllMedicines().first()

        // Assert
        assert(result == medicines)
    }

    @Test
    fun `getMedicineById should return medicine from dao`() = runTest {
        // Arrange
        val medicine = Medicine(id = "1", name = "aspirin", dose = "", strength = "500 mg")
        coEvery { dao.getMedicineById("1") } returns medicine

        // Act
        val result = repository.getMedicineById("1")

        // Assert
        assert(result == medicine)
    }

    @Test
    fun `fetchMedicinesFromNetwork should fetch from api and insert into dao`() = runTest {
        // Arrange
        val response = MedicineResponse(
            problems = listOf(
                Problem(
                    diabetes = listOf(
                        Diabetes(
                            medications = listOf(
                                Medication(
                                    medicationsClasses = listOf(
                                        MedicationsClass(
                                            data = listOf(
                                                DataItem(
                                                    associatedDrug = listOf(
                                                        AssociatedDrug(
                                                            id = "1",
                                                            name = "aspirin",
                                                            dose = "",
                                                            strength = "500 mg"
                                                        )
                                                    )
                                                )
                                            )
                                        )
                                    )
                                )
                            ),
                            labs = null
                        )
                    ),
                    asthma = null
                )
            )
        )
        coEvery { api.getMedicines() } returns listOf(response)

        // Act
        repository.fetchMedicinesFromNetwork()

        // Assert
        coVerify { api.getMedicines() }
        coVerify {
            dao.insertMedicines(match {
                it.size == 1 && it[0].name == "aspirin"
            })
        }
    }
}