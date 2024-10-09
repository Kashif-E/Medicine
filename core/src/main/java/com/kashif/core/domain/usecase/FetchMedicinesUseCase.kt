package com.kashif.core.domain.usecase

import com.kashif.core.domain.repository.IMedicineRepository
import javax.inject.Inject

class FetchMedicinesUseCase @Inject constructor(
    private val repository: IMedicineRepository
) {
    suspend operator fun invoke() {
        repository.fetchMedicinesFromNetwork()
    }
}