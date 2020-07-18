package com.kurt.jokes.mobile.presentation.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual var mainDispatcher: CoroutineDispatcher = Dispatchers.Main
actual var ioDispatcher: CoroutineDispatcher = Dispatchers.IO