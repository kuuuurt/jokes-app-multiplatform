package com.kurt.jokes.mobile.presentation.helpers

/**
 * Copyright 2020, White Cloak Technologies, Inc., All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 01/01/2020
 */

import com.kurt.jokes.mobile.presentation.defaultDispatcher
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*
import kotlinx.io.core.Closeable

fun <T> ConflatedBroadcastChannel<T>.wrap(): CFlow<T> = CFlow(asFlow())

fun <T> Flow<T>.wrap(): CFlow<T> = CFlow(this)

class CFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {
    fun watch(block: (T) -> Unit): Closeable {
        val job = Job(/*ConferenceService.coroutineContext[Job]*/)

        onEach {
            block(it)
        }.launchIn(CoroutineScope(defaultDispatcher + job))

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}