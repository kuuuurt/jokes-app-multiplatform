package com.kurt.jokes.mobile

import kotlinx.coroutines.CoroutineScope
import org.junit.Rule

actual open class CommonTest {
    @get:Rule
    val rule = CoroutineTestRule()
    actual fun runBlocking(block: suspend CoroutineScope.() -> Unit) = kotlinx.coroutines.runBlocking {
        block()
    }
}