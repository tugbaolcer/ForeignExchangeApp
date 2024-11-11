package com.tugbaolcer.foreignexchangeapp.data.repository.base

import com.tugbaolcer.foreignexchangeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseRepositoryImpl {

    protected fun <T> safeApiCall(apiCall: suspend () -> T): Flow<Resource<T>> = flow {
        try {
            emit(Resource.Loading())
            val response = apiCall()
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error("Bir hata oluştu: ${e.localizedMessage}", null))
        }
    }
}
