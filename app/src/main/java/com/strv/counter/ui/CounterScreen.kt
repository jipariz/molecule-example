package com.strv.counter.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CounterScreen(
    viewModel: CounterViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    SampleScreen(
        state = state,
        onCounterButtonClicked = viewModel::onCounterButtonClicked,
    )
}

@Composable
private fun SampleScreen(
    state: CounterState,
    onCounterButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.align(CenterHorizontally),
            text = state.count.toString(),
            style = MaterialTheme.typography.headlineLarge,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier
                .align(CenterHorizontally),
            onClick = onCounterButtonClicked,
        ) {
            Text("Click me")
        }
    }
}
