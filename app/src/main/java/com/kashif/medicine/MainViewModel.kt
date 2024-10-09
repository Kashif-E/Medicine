package com.kashif.medicine


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kashif.core.di.ioDispatcher
import com.kashif.core.domain.usecase.GetLoggedInUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLoggedInUserUseCase: GetLoggedInUserUseCase,
    @ioDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _isUserLoggedIn = MutableStateFlow<Pair<String?, Boolean?>?>(null)
    val isUserLoggedIn = _isUserLoggedIn.asStateFlow()

    init {
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        viewModelScope.launch(ioDispatcher) {
            getLoggedInUserUseCase().collect { user ->
                _isUserLoggedIn.update { Pair(user?.email, user != null) }
            }
        }
    }
}