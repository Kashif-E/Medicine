package com.kashif.core.domain.usecase

import com.kashif.core.domain.model.Medicine
import com.kashif.core.domain.repository.MedicineRepository

class InsertMedicinesUseCase(
    private val repository: MedicineRepository
) {
    suspend operator fun invoke(medicines: List<Medicine>) {
        repository.insertMedicines(medicines)
    }
}