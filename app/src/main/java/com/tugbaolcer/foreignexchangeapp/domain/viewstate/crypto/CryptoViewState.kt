package com.tugbaolcer.foreignexchangeapp.domain.viewstate.crypto

import com.tugbaolcer.foreignexchangeapp.data.network.dto.CryptoDto
import com.tugbaolcer.foreignexchangeapp.domain.viewstate.IViewState

data class CryptoViewState(
    val isLoading: Boolean = false,
    val data: List<CryptoDto.Result>? = null,
    var errorMessage: String? = null
) : IViewState
