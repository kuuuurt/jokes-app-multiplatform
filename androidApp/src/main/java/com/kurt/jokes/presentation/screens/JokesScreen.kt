package com.kurt.jokes.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kurt.jokes.mobile.domain.entities.Joke
import com.kurt.jokes.mobile.presentation.features.jokes.JokesViewModel
import com.kurt.jokes.mobile.presentation.models.UiState
import com.kurt.jokes.presentation.MainViewModelFactory

@Composable
fun JokesScreen() {
    val viewModel = viewModel<JokesViewModel>(factory = MainViewModelFactory())
    val jokes = viewModel.jokes.collectAsState(listOf())
    val getJokesState = viewModel.jokesState.collectAsState(UiState.Loading)

    if (getJokesState.value == UiState.Loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(jokes.value) {
                JokeItem(it)
            }
        }
    }
}

@Composable
fun JokeItem(joke: Joke) {
    val isPunchlineVisible = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.clickable { isPunchlineVisible.value = !isPunchlineVisible.value }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                joke.setup,
                style = MaterialTheme.typography.h6
            )
            if (isPunchlineVisible.value) {
                Text(joke.punchline)
            }
        }
        Divider()
    }
}