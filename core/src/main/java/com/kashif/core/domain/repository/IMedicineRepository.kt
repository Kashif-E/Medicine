package com.kashif.core.domain.repository


import com.kashif.core.domain.model.Medicine
import kotlinx.coroutines.flow.Flow

interface IMedicineRepository {
    suspend fun insertMedicines(medicines: List<Medicine>)
    fun getAllMedicines(): Flow<List<Medicine>>
    suspend fun getMedicineById(id: Int): Medicine?
    suspend fun fetchMedicinesFromNetwork()
}