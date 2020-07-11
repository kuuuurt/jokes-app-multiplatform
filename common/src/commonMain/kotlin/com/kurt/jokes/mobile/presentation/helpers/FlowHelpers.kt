package com.kurt.jokes.mobile.presentation.helpers

import com.kurt.jokes.mobile.presentation.base.mainDispatcher
import io.ktor.utils.io.core.Closeable
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * Copyright 2020, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 01/01/2020
 */

fun <T> Flow<T>.asCommonFlow(): CFlow<T> = CFlow(this)
class CFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {
    fun watch(block: (T) -> Unit): Closeable {
        val job = Job()

        onEach {
            block(it)
        }.launchIn(CoroutineScope(mainDispatcher + job))

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}