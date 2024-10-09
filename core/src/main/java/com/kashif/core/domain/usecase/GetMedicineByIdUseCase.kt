package com.kashif.core.domain.usecase

import com.kashif.core.domain.repository.IMedicineRepository
import com.kashif.core.domain.model.Medicine
import javax.inject.Inject


class GetMedicineByIdUseCase @Inject constructor(
    private val repository: IMedicineRepository
) {
    suspend operator fun invoke(id: String): Medicine? {
        return repository.getMedicineById(id)
    }
}