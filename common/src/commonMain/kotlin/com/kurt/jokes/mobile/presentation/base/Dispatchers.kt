package com.kurt.jokes.mobile.presentation.base

import kotlinx.coroutines.CoroutineDispatcher

expect val mainDispatcher: CoroutineDispatcher
expect val ioDispatcher: CoroutineDispatcher