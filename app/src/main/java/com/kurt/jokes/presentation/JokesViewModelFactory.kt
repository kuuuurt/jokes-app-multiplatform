package com.kurt.jokes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kurt.jokes.mobile.di.ServiceLocator
import com.kurt.jokes.mobile.domain.usecases.GetJokes

class JokesViewModelFactory(private val getJokes: GetJokes) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JokesViewModel::class.java)) {
            return JokesViewModel(getJokes) as T
        }
        throw IllegalArgumentException("ViewModel not found!")
    }
}