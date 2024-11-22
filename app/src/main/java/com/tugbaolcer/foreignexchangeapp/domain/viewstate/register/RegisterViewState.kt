package com.tugbaolcer.foreignexchangeapp.domain.viewstate.register

import androidx.compose.runtime.Stable
import com.tugbaolcer.foreignexchangeapp.domain.viewstate.IViewState

@Stable
data class RegisterViewState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
): IViewState