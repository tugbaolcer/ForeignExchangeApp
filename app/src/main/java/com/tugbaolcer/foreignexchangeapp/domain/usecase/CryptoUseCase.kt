package com.tugbaolcer.foreignexchangeapp.domain.usecase

import com.tugbaolcer.foreignexchangeapp.domain.base.BaseUseCase
import com.tugbaolcer.foreignexchangeapp.domain.model.Crypto
import com.tugbaolcer.foreignexchangeapp.domain.repository.CryptoRepository
import com.tugbaolcer.foreignexchangeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CryptoUseCase @Inject constructor(private val repository: CryptoRepository): BaseUseCase<List<Crypto>> {
    override suspend fun invoke(): Flow<Resource<List<Crypto>>> {
        return repository.getCrypto()
    }
}