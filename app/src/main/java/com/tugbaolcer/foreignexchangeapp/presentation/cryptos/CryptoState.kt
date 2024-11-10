package com.tugbaolcer.foreignexchangeapp.presentation.cryptos

import com.tugbaolcer.foreignexchangeapp.domain.model.Crypto


data class CryptoState(
    val isLoading:Boolean = false,
    val crypto: List<Crypto> = emptyList(),
    val error:String =""
)