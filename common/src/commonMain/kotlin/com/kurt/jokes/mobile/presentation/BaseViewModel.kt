package com.kurt.jokes.mobile.presentation

import kotlinx.coroutines.CoroutineScope

/**
 * Copyright 2020, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 01/01/2020
 */

expect open class BaseViewModel() {
    val clientScope: CoroutineScope
    protected open fun onCleared()
}