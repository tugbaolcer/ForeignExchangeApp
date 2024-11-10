package com.tugbaolcer.foreignexchangeapp.domain.repository

import com.tugbaolcer.foreignexchangeapp.domain.model.Crypto
import com.tugbaolcer.foreignexchangeapp.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Bu
 */
interface CryptoRepository {
    fun getCrypto(): Flow<Resource<List<Crypto>>>
}