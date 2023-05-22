package com.strv.counter.ui

sealed class CounterEvents {
    object IncrementCounter: CounterEvents()
}