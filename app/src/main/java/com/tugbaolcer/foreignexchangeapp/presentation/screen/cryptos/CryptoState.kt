package com.tugbaolcer.foreignexchangeapp.presentation.screen.cryptos

import com.tugbaolcer.foreignexchangeapp.data.network.dto.CryptoDto

data class CryptoState(
    val isLoading:Boolean = false,
    val crypto: List<CryptoDto.Result> = emptyList(),
    val error:String =""
)