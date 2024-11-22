package com.tugbaolcer.foreignexchangeapp.presentation.screen.hub.login

import androidx.lifecycle.viewModelScope
import com.tugbaolcer.foreignexchangeapp.domain.usecase.LoginUseCase
import com.tugbaolcer.foreignexchangeapp.domain.viewstate.IViewEvent
import com.tugbaolcer.foreignexchangeapp.domain.viewstate.login.LoginViewState
import com.tugbaolcer.foreignexchangeapp.presentation.base.BaseViewModel
import com.tugbaolcer.foreignexchangeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: LoginUseCase
) : BaseViewModel<LoginViewState, LoginEvent>() {

    override fun createInitialState() = LoginViewState()

    override fun onTriggerEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.LoginButtonClicked -> loginUser(
                email = event.email,
                password = event.password
            )
            LoginEvent.LogoutClicked -> logoutUser()
        }
    }

    private fun loginUser(email: String, password: String) {
        setState { copy(isLoading = true) }

        viewModelScope.launch {
            call(loginUserUseCase.invoke(email, password)) { result ->
                when (result) {
                    is Resource.Success<*> -> {
                        setState { copy(isLoading = false, isLoggedIn = true) }
                    }
                    is Resource.Error<*> -> {
                        setState { copy(isLoading = false, errorMessage = result.message) }
                    }

                    else -> {}
                }
            }
        }
    }

    private fun logoutUser() {
        setState { copy(isLoggedIn = false) }
    }

    fun clearErrorMessage() {
        setState { copy(errorMessage = null) }
    }

}

sealed class LoginEvent : IViewEvent {
    data class LoginButtonClicked(val email: String, val password: String) : LoginEvent()
    object LogoutClicked : LoginEvent()
}


