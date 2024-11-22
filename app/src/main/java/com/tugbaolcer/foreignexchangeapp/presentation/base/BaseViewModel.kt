package com.tugbaolcer.foreignexchangeapp.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tugbaolcer.foreignexchangeapp.domain.viewstate.IViewEvent
import com.tugbaolcer.foreignexchangeapp.domain.viewstate.IViewState
import com.tugbaolcer.foreignexchangeapp.util.Resource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : IViewState, Event : IViewEvent> : ViewModel() {

    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    val currentState: State get() = uiState.value

    abstract fun onTriggerEvent(event: Event)

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState: StateFlow<State> = _uiState

    private val _uiEvent: MutableSharedFlow<Event> = MutableSharedFlow()
    val uiEvent = _uiEvent.asSharedFlow()

    protected fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }

    protected fun setEvent(event: Event) {
        viewModelScope.launch { _uiEvent.emit(event) }
    }

    protected suspend fun <T> call(
        callFlow: Flow<Resource<T>>,
        completionHandler: (collect: Resource<T>) -> Unit = {}
    ) {
        callFlow
            .catch { }
            .collect {
                completionHandler.invoke(it)
            }
    }
}

