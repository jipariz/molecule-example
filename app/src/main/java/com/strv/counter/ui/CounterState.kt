package com.strv.counter.ui

data class CounterState(
    val count: Int,
) {
    companion object {
        val initial = CounterState(
            count = 0,
        )
    }
}