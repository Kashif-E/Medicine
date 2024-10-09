package com.kashif.feature_login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kashif.core.domain.usecase.FetchMedicinesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val fetchMedicinesUseCase: FetchMedicinesUseCase
) : ViewModel() {

    fun fetchMedicines() {
        viewModelScope.launch {
            fetchMedicinesUseCase()
        }
    }
}