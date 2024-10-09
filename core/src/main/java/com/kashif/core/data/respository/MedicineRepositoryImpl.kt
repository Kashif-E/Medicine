package com.kashif.core.data.respository


import com.kashif.core.data.local.MedicineDao
import com.kashif.core.data.remote.MedicineApi
import com.kashif.core.domain.model.Medicine
import com.kashif.core.domain.model.asDomainModel
import com.kashif.core.domain.repository.MedicineRepository
import kotlinx.coroutines.flow.Flow


class MedicineRepositoryImpl(
    private val dao: MedicineDao,
    private val api: MedicineApi
) : MedicineRepository {

    override suspend fun insertMedicines(medicines: List<Medicine>) {
        dao.insertMedicines(medicines)
    }

    override fun getAllMedicines(): Flow<List<Medicine>> {
        return dao.getAllMedicines()
    }

    override suspend fun getMedicineById(id: Int): Medicine? {
        return dao.getMedicineById(id)
    }

    override suspend fun fetchMedicinesFromNetwork(): List<Medicine> {
        val response = api.getMedicines()
        val medicines = response.asDomainModel()
        dao.insertMedicines(medicines)
        return medicines
    }
}