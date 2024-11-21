package com.tugbaolcer.foreignexchangeapp.presentation.hub.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tugbaolcer.foreignexchangeapp.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase(email, password)
                .onStart {
                    _uiState.value = LoginUiState.Loading
                }
                .catch { e ->
                    _uiState.value = LoginUiState.Error(e.message ?: "An error occurred")
                }
                .collect { result ->
                    result.onSuccess {
                        _uiState.value = LoginUiState.Success
                    }.onFailure { error ->
                        _uiState.value = LoginUiState.Error(error.message ?: "Login failed")
                    }
                }
        }
    }
}

sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    object Success : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}
