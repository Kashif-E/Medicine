package com.kashif.core.domain.usecase

import com.kashif.core.domain.repository.IMedicineRepository
import com.kashif.core.domain.model.Medicine
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMedicinesUseCase @Inject constructor(
    private val repository: IMedicineRepository
) {
    operator fun invoke(): Flow<List<Medicine>> {
        return repository.getAllMedicines()
    }
}