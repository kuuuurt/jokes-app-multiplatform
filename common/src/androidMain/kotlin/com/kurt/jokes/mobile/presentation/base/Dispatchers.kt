package com.kurt.jokes.mobile.presentation.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual val defaultDispatcher: CoroutineDispatcher
    get() = Dispatchers.Main