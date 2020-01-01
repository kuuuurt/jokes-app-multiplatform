package com.kurt.jokes.presentation

sealed class UiState<out T> {
    class Success<out T>(val data: T) : UiState<T>()
    class Complete<out T> : UiState<T>()
    class Loading<out T> : UiState<T>()
    class Error<out T>(throwable: Throwable) : UiState<T>()
}