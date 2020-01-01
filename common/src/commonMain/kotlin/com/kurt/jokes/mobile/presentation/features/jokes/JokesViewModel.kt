package com.kurt.jokes.mobile.presentation.features.jokes

import com.kurt.jokes.mobile.di.ServiceLocator
import com.kurt.jokes.mobile.domain.entities.Joke
import com.kurt.jokes.mobile.domain.usecases.GetJokes
import com.kurt.jokes.mobile.presentation.models.UiState
import com.kurt.jokes.mobile.presentation.base.BaseViewModel
import com.kurt.jokes.mobile.presentation.helpers.wrap
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.launch
import kotlin.native.concurrent.ThreadLocal

@ExperimentalCoroutinesApi
class JokesViewModel(private val getJokes: GetJokes) : BaseViewModel() {
    private val _jokesState = ConflatedBroadcastChannel<UiState>()
    val jokesState = _jokesState.wrap()

    private val _jokes = ConflatedBroadcastChannel<List<Joke>>()
    val jokes = _jokes.wrap()

    init {
        clientScope.launch(CoroutineExceptionHandler { _, throwable ->
            _jokesState.offer(
                UiState.Error(
                    throwable
                )
            )
        }) {
            _jokesState.offer(UiState.Loading)
            _jokes.offer(getJokes())
            _jokesState.offer(UiState.Success)
        }
    }

    @ThreadLocal
    companion object {
        fun create() =
            JokesViewModel(
                ServiceLocator.getJokes
            )
    }
}