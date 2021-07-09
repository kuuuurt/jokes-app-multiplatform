package com.kurt.jokes.mobile.data

import com.kurt.jokes.mobile.data.local.JokesLocalSource
import com.kurt.jokes.mobile.data.remote.JokesRemoteSource
import com.kurt.jokes.mobile.domain.entities.Joke
import com.kurt.jokes.mobile.domain.repositories.JokesRepository
import com.kurt.jokes.mobile.presentation.base.ioDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class RealJokesRepository(
    private val jokesRemoteSource: JokesRemoteSource,
    private val jokesLocalSource: JokesLocalSource
) : JokesRepository {
    override suspend fun getJokes() = withContext(ioDispatcher) {
        try {
            val jokes = jokesRemoteSource.getJokes()
            jokesLocalSource.saveJokes(jokes)
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
        jokesLocalSource.getJokes()
    }
}