package com.kurt.jokes.mobile.presentation.models

sealed class UiState {
    object Success : UiState()
    object Loading : UiState()
    class Error(val throwable: Throwable) : UiState()
}