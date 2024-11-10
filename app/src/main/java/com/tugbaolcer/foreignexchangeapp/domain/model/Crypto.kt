package com.tugbaolcer.foreignexchangeapp.domain.model

data class Crypto(
    val name: String,
    val pricestr: String,
    val currency: String,
    val code: String
)