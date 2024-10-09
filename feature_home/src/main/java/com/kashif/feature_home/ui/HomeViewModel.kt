package com.kashif.feature_home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kashif.core.domain.model.Medicine
import com.kashif.core.domain.usecase.GetMedicinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getMedicinesUseCase: GetMedicinesUseCase
) : ViewModel() {

    val medicines: StateFlow<List<Medicine>> = getMedicinesUseCase()
        .flowOn(Dispatchers.IO)
        .map { it }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}