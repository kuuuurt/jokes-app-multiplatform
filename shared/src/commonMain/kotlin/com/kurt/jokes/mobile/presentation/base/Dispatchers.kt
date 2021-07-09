package com.kurt.jokes.mobile.presentation.base

import kotlinx.coroutines.CoroutineDispatcher

expect var mainDispatcher: CoroutineDispatcher
expect val ioDispatcher: CoroutineDispatcher