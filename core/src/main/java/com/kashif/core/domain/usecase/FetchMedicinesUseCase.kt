package com.kashif.core.domain.usecase

import com.kashif.core.domain.repository.MedicineRepository

class FetchMedicinesUseCase(
    private val repository: MedicineRepository
) {
    suspend operator fun invoke() {
        repository.fetchMedicinesFromNetwork()
    }
}