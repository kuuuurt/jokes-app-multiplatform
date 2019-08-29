package com.kurt.jokes.jokes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kurt.jokes.mobile.di.ServiceLocator
import com.kurt.jokes.mobile.domain.usecases.GetJokes

class JokesViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JokesViewModel::class.java)) {
            return JokesViewModel(ServiceLocator.getJokes) as T
        }
        throw IllegalArgumentException("ViewModel not found!")
    }
}