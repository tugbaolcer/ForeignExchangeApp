package com.tugbaolcer.foreignexchangeapp.domain.viewstate.stock

import com.tugbaolcer.foreignexchangeapp.data.network.dto.StockDto
import com.tugbaolcer.foreignexchangeapp.domain.viewstate.IViewState

data class StockViewState(
    val isLoading: Boolean = false,
    val data: List<StockDto.StockDtoItem>? = null,
    var errorMessage: String? = null
) : IViewState