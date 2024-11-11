package com.tugbaolcer.foreignexchangeapp.data.repository

import com.tugbaolcer.foreignexchangeapp.data.dto.AppApi
import com.tugbaolcer.foreignexchangeapp.data.dto.CryptoDto
import com.tugbaolcer.foreignexchangeapp.data.repository.base.BaseRepositoryImpl
import com.tugbaolcer.foreignexchangeapp.domain.repository.CryptoRepository
import com.tugbaolcer.foreignexchangeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(private val appApi: AppApi): BaseRepositoryImpl(), CryptoRepository {
    override fun getCrypto(): Flow<Resource<List<CryptoDto.Result>>> = safeApiCall {
        appApi.getCrypto().result
    }
}