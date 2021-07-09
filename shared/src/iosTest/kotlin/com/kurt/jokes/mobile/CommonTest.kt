package com.kurt.jokes.mobile

import kotlinx.coroutines.CoroutineScope

actual open class CommonTest {
    actual fun runBlocking(block: suspend CoroutineScope.() -> Unit) = kotlinx.coroutines.runBlocking {
        block()
    }
}