package com.kashif.feature_login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kashif.core.domain.model.User
import com.kashif.core.domain.usecase.FetchMedicinesUseCase
import com.kashif.core.domain.usecase.InsertUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val fetchMedicinesUseCase: FetchMedicinesUseCase,
    private val insertUserUseCase: InsertUserUseCase
) : ViewModel() {

    fun login(email: String) {
        viewModelScope.launch {
            fetchMedicinesUseCase()
            insertUserUseCase(User(email = email))
        }
    }

}