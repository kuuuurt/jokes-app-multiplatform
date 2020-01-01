package com.kurt.jokes.mobile.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

/**
 * Copyright 2020, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 01/01/2020
 */

actual open class BaseViewModel actual constructor(): ViewModel() {
    actual val clientScope: CoroutineScope = viewModelScope
    actual override fun onCleared() {
        super.onCleared()
    }
}