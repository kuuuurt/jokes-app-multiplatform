package com.kurt.jokes.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kurt.jokes.mobile.domain.entities.Joke
import com.kurt.jokes.mobile.domain.usecases.GetJokes
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class JokesViewModel(private val getJokes: GetJokes) : ViewModel() {
    private val _jokesState = MutableLiveData<UiState<List<Joke>>>()
    val jokesState: LiveData<UiState<List<Joke>>> = _jokesState

    init {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            _jokesState.value = UiState.Error(throwable)
        }) {
            _jokesState.value = UiState.Loading()
            _jokesState.value = UiState.Success(getJokes())
        }
    }
}