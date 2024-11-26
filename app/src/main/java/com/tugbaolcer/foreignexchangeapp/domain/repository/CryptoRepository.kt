package com.tugbaolcer.foreignexchangeapp.domain.repository

import com.tugbaolcer.foreignexchangeapp.data.network.dto.CryptoDto
import com.tugbaolcer.foreignexchangeapp.data.network.dto.StockDto
import com.tugbaolcer.foreignexchangeapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {
    fun getCrypto(): Flow<Resource<List<CryptoDto.Result>>>
    fun getStocks(): Flow<Resource<List<StockDto.StockDtoItem>>>
}