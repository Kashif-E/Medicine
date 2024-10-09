package com.kashif.core.domain.usecase

import com.kashif.core.domain.model.Medicine
import com.kashif.core.domain.repository.MedicineRepository
import kotlinx.coroutines.flow.Flow

class GetMedicinesUseCase(
    private val repository: MedicineRepository
) {
    operator fun invoke(): Flow<List<Medicine>> {
        return repository.getAllMedicines()
    }
}