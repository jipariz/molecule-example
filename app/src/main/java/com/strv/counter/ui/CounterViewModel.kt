package com.strv.counter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.molecule.AndroidUiDispatcher
import app.cash.molecule.RecompositionClock
import app.cash.molecule.launchMolecule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(
    private val counterPresenter: CounterPresenter
) : ViewModel() {

    private val moleculeScope = CoroutineScope(viewModelScope.coroutineContext + AndroidUiDispatcher.Main)

    private val moleculeEvents = MutableSharedFlow<CounterEvents>()

    val state: StateFlow<CounterState> = moleculeScope.launchMolecule(clock = RecompositionClock.ContextClock){
        counterPresenter.present(events = moleculeEvents)
    }

    fun onCounterButtonClicked() {
        viewModelScope.launch {
            moleculeEvents.emit(CounterEvents.IncrementCounter)
        }
    }
}


