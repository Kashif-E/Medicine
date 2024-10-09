package com.kashif.core.data.respository


import com.kashif.core.data.local.MedicineDao
import com.kashif.core.data.remote.MedicineApi
import com.kashif.core.di.ioDispatcher
import com.kashif.core.domain.model.Medicine
import com.kashif.core.domain.model.asDomainModel
import com.kashif.core.domain.repository.IMedicineRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MedicineRepositoryImpl @Inject constructor(
    private val dao: MedicineDao,
    private val api: MedicineApi,
    @ioDispatcher private val ioDispatcher: CoroutineDispatcher,
) : IMedicineRepository {

    override suspend fun insertMedicines(medicines: List<Medicine>) {
        dao.insertMedicines(medicines)
    }

    override fun getAllMedicines(): Flow<List<Medicine>> {
           return dao.getAllMedicines()
    }

    override suspend fun getMedicineById(id: Int): Medicine? {
        return withContext(ioDispatcher) {
            dao.getMedicineById(id)
        }
    }

    override suspend fun fetchMedicinesFromNetwork() {
        withContext(ioDispatcher) {
            val response = api.getMedicines().first()
            val medicines = response.asDomainModel()
            dao.insertMedicines(medicines)

        }
    }
}