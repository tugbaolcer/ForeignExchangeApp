package com.tugbaolcer.foreignexchangeapp.presentation.screen.hub.register

import androidx.lifecycle.viewModelScope
import com.tugbaolcer.foreignexchangeapp.data.dto.UserDto
import com.tugbaolcer.foreignexchangeapp.domain.usecase.RegisterUseCase
import com.tugbaolcer.foreignexchangeapp.domain.viewstate.IViewEvent
import com.tugbaolcer.foreignexchangeapp.domain.viewstate.register.RegisterViewState
import com.tugbaolcer.foreignexchangeapp.presentation.base.BaseViewModel
import com.tugbaolcer.foreignexchangeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUseCase
) : BaseViewModel<RegisterViewState, RegisterEvent>() {

    override fun createInitialState(): RegisterViewState {
        return RegisterViewState()
    }

    override fun onTriggerEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.RegisterButtonClicked -> registerUser(
                UserDto(
                    name = event.name,
                    surname = event.surname,
                    email = event.email,
                    password = event.password
                )
            )

           is RegisterEvent.ClearFields -> clearFields()
        }
    }

    private fun registerUser(userDto: UserDto) {
        setState { copy(isLoading = true) }

        viewModelScope.launch {
            call(registerUserUseCase.invoke(userDto)) { result ->
                when (result) {
                    is Resource.Success -> {
                        setState { copy(isLoading = false, isSuccess = true) }
                    }
                    is Resource.Error -> {
                        setState { copy(isLoading = false, errorMessage = result.message) }
                    }
                    is Resource.Loading -> {
                        setState { copy(isLoading = true) }
                    }
                }
            }
        }
    }


    private fun clearFields() {
        setState { copy(isSuccess = false, errorMessage = null) }
    }
}


sealed class RegisterEvent : IViewEvent {
    data class RegisterButtonClicked(
        val name: String,
        val surname: String,
        val email: String,
        val password: String
    ) : RegisterEvent()

    object ClearFields : RegisterEvent()
}
