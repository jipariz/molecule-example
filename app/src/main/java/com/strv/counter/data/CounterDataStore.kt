package com.strv.counter.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CounterDataStore @Inject constructor() {
    private val _state: MutableStateFlow<Int> = MutableStateFlow(0)
    val state = _state.asStateFlow()

    fun incrementCounter() {
        _state.tryEmit(_state.value + 1)
    }
}