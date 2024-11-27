package com.tugbaolcer.foreignexchangeapp.presentation.screen.cryptos

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.tugbaolcer.foreignexchangeapp.domain.usecase.CryptoUseCase
import com.tugbaolcer.foreignexchangeapp.domain.viewstate.crypto.CryptoViewState
import com.tugbaolcer.foreignexchangeapp.presentation.base.BaseViewModel
import com.tugbaolcer.foreignexchangeapp.presentation.screen.stocks.StockViewEvent
import com.tugbaolcer.foreignexchangeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(
    private val cryptoUseCase: CryptoUseCase
) : BaseViewModel<CryptoViewState, StockViewEvent>() {

    init {
        getStock()
    }

    private fun getStock() {
        viewModelScope.launch {
            setState { currentState.copy(isLoading = true) }
            delay(2000)
            cryptoUseCase.invoke().collect{
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

    override fun createInitialState() = CryptoViewState()
    override fun onTriggerEvent(event: StockViewEvent) {}

}