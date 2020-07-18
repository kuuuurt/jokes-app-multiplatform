package com.kurt.jokes.mobile

import kotlinx.coroutines.CoroutineScope

expect open class CommonTest() {
    fun runBlocking(block: suspend CoroutineScope.() -> Unit)
}