package com.kurt.jokes.jokes.presentation

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
    private val _jokes = MutableLiveData<List<Joke>>()
    val jokes: LiveData<List<Joke>> = _jokes

    init {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            Log.d("Test", throwable.message)
        }) {
            _jokes.value = getJokes()
        }
    }
}