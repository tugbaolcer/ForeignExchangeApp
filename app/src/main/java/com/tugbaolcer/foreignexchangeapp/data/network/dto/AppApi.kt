package com.tugbaolcer.foreignexchangeapp.data.network.dto

import retrofit2.http.GET

interface AppApi {
    @GET("economy/cripto")
    suspend fun getCrypto(): CryptoDto

    @GET("economy/hisseSenedi")
    suspend fun getStocks(): StockDto
}