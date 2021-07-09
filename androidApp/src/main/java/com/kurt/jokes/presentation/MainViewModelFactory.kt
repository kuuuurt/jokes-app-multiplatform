package com.kurt.jokes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kurt.jokes.mobile.presentation.features.jokes.JokesViewModel

class MainViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when {
        modelClass.isAssignableFrom(JokesViewModel::class.java) -> {
            JokesViewModel.create()
        }
        else -> throw IllegalArgumentException("Unknown ViewModel class")
    } as T
}