package com.tugbaolcer.foreignexchangeapp.domain.usecase

import com.tugbaolcer.foreignexchangeapp.data.dto.CryptoDto
import com.tugbaolcer.foreignexchangeapp.domain.base.BaseUseCase
import com.tugbaolcer.foreignexchangeapp.domain.repository.CryptoRepository
import com.tugbaolcer.foreignexchangeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CryptoUseCase @Inject constructor(private val repository: CryptoRepository): BaseUseCase<List<CryptoDto.Result>> {
    override suspend fun invoke(): Flow<Resource<List<CryptoDto.Result>>> {
        return repository.getCrypto()
    }
}