package com.kurt.jokes.mobile.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

/**
 * Copyright 2020, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 01/01/2020
 */

actual open class BaseViewModel actual constructor() {
    private val viewModelJob = SupervisorJob()
    val viewModelScope: CoroutineScope = CoroutineScope(defaultDispatcher + viewModelJob)

    actual val clientScope: CoroutineScope = viewModelScope

    protected actual open fun onCleared() {
        viewModelJob.cancelChildren()
    }
}