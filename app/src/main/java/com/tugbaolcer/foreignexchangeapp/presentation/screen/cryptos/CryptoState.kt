package com.tugbaolcer.foreignexchangeapp.presentation.screen.cryptos

import com.tugbaolcer.foreignexchangeapp.data.dto.CryptoDto

data class CryptoState(
    val isLoading:Boolean = false,
    val crypto: List<CryptoDto.Result> = emptyList(),
    val error:String =""
)