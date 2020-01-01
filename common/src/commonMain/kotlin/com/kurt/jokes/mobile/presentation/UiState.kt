package com.kurt.jokes.mobile.presentation

sealed class UiState {
    object Success : UiState()
    object Complete : UiState()
    object Loading : UiState()
    class Error(throwable: Throwable) : UiState()
}