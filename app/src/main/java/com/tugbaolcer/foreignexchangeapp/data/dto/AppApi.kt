package com.tugbaolcer.foreignexchangeapp.data.dto

import retrofit2.http.GET

interface AppApi {
    @GET("economy/cripto")
    suspend fun getCrypto(): CryptoDto
}