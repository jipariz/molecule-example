package com.strv.counter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.strv.counter.data.CounterDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(
    private val counterDataStore: CounterDataStore
) : ViewModel() {
    val state: StateFlow<CounterState> = combine(
        counterDataStore.state,
        flowOf(Unit),
    ) { exampleCounter, _ ->
        CounterState(
            count = exampleCounter,
        )
    }.stateIn(
        scope = this.viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = CounterState.initial
    )

    fun onCounterButtonClicked() {
        viewModelScope.launch {
            counterDataStore.incrementCounter()
        }
    }
}