package com.strv.counter.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.strv.counter.data.CounterDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CounterPresenter @Inject constructor(
    val counterDataStore: CounterDataStore
) {
    @Composable
    fun Present(
        events: Flow<CounterEvents>,
    ): CounterState {
        val currentCount by counterDataStore.state.collectAsState()
        LaunchedEffect(Unit){
            events.collect {
                when(it){
                    CounterEvents.IncrementCounter -> counterDataStore.incrementCounter()
                }
            }
        }
        return CounterState(count = currentCount)
    }
}

