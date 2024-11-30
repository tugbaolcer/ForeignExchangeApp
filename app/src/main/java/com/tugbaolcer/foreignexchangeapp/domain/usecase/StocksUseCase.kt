package com.tugbaolcer.foreignexchangeapp.domain.usecase

import com.tugbaolcer.foreignexchangeapp.data.network.dto.StockDto
import com.tugbaolcer.foreignexchangeapp.domain.base.BaseUseCase
import com.tugbaolcer.foreignexchangeapp.domain.repository.CryptoRepository
import com.tugbaolcer.foreignexchangeapp.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StocksUseCase @Inject constructor(private val repository: CryptoRepository):
    BaseUseCase<List<StockDto.StockDtoItem>> {
    override suspend fun invoke(): Flow<Resource<List<StockDto.StockDtoItem>>> {
        return repository.getStocks()
    }
}