package com.tugbaolcer.foreignexchangeapp.domain.viewstate.login

import com.tugbaolcer.foreignexchangeapp.domain.viewstate.IViewState

data class LoginViewState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    var errorMessage: String? = null
) : IViewState
