package com.tugbaolcer.foreignexchangeapp.presentation.screen.stock

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.tugbaolcer.foreignexchangeapp.domain.repository.CryptoRepository
import com.tugbaolcer.foreignexchangeapp.domain.viewstate.IViewEvent
import com.tugbaolcer.foreignexchangeapp.domain.viewstate.stock.StockViewState
import com.tugbaolcer.foreignexchangeapp.presentation.base.BaseViewModel
import com.tugbaolcer.foreignexchangeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockViewModel @Inject constructor(
    private val cryptoRepository: CryptoRepository
): BaseViewModel<StockViewState, StockViewEvent>(){

    init {
        getStock()
    }

    private fun getStock() {
        viewModelScope.launch {
            setState { currentState.copy(isLoading = true) }
            delay(2000)
            cryptoRepository.getStocks().collect {
                when (it) {
                    is Resource.Success -> {
                        setState { currentState.copy(data = it.data, isLoading = false) }
                    }
                    is Resource.Error -> {
                        Log.e("StockViewModel", "Hata Mesajı: ${it.message}")

                        setState { currentState.copy(isLoading = false) }
                        setEvent(StockViewEvent.SnackBarError(it.message))

                    }
                    is Resource.Loading -> {
                        setState { currentState.copy(isLoading = true) }
                    }
                }
            }

        }
    }

    override fun createInitialState() = StockViewState()
    override fun onTriggerEvent(event: StockViewEvent) {}
}

sealed class StockViewEvent : IViewEvent {
    class SnackBarError(val message: String?) : StockViewEvent()
}