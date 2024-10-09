package com.kashif.core.domain.usecase

import com.kashif.core.domain.model.Medicine
import com.kashif.core.domain.repository.MedicineRepository

class GetMedicineByIdUseCase(
    private val repository: MedicineRepository
) {
    suspend operator fun invoke(id: Int): Medicine? {
        return repository.getMedicineById(id)
    }
}