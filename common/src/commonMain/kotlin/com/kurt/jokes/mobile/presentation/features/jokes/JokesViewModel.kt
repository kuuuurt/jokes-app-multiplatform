package com.kurt.jokes.mobile.presentation.features.jokes

import com.kurt.jokes.mobile.ServiceLocator
import com.kurt.jokes.mobile.domain.entities.Joke
import com.kurt.jokes.mobile.domain.usecases.GetJokes
import com.kurt.jokes.mobile.presentation.models.UiState
import com.kurt.jokes.mobile.presentation.base.BaseViewModel
import com.kurt.jokes.mobile.presentation.helpers.CFlow
import com.kurt.jokes.mobile.presentation.helpers.asCommonFlow
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import kotlin.native.concurrent.ThreadLocal

@OptIn(ExperimentalCoroutinesApi::class)
class JokesViewModel(private val getJokes: GetJokes) : BaseViewModel() {
    private val _jokes = MutableStateFlow<List<Joke>?>(null)
    val jokes: CFlow<List<Joke>> get() = _jokes.filterNotNull().asCommonFlow()

    private val _jokesState = MutableStateFlow<UiState?>(null)
    val jokesState: CFlow<UiState> get() = _jokesState.filterNotNull().asCommonFlow()

    init {
        clientScope.launch(CoroutineExceptionHandler { _, throwable ->
            _jokesState.value = UiState.Error(throwable)
        }) {
            _jokesState.value = UiState.Loading
            _jokes.value = getJokes()
            _jokesState.value = UiState.Success
        }
    }

    @ThreadLocal
    companion object {
        fun create() = JokesViewModel(ServiceLocator.getJokes)
    }
}