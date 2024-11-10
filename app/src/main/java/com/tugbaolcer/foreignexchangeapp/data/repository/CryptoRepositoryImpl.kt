package com.tugbaolcer.foreignexchangeapp.data.repository

import com.tugbaolcer.foreignexchangeapp.data.dto.AppApi
import com.tugbaolcer.foreignexchangeapp.data.dto.toCrypto
import com.tugbaolcer.foreignexchangeapp.domain.model.Crypto
import com.tugbaolcer.foreignexchangeapp.domain.repository.CryptoRepository
import com.tugbaolcer.foreignexchangeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CryptoRepositoryImpl @Inject constructor(private val appApi: AppApi): CryptoRepository {
    override fun getCrypto(): Flow<Resource<List<Crypto>>> = flow {
        try {
            val response = appApi.getCrypto().toCrypto()
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error("Bir hata oluştu", null))
            e.printStackTrace()
        }
    }
}