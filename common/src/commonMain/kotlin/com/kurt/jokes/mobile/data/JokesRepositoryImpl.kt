package com.kurt.jokes.mobile.data

import com.kurt.jokes.mobile.data.local.JokesLocalSource
import com.kurt.jokes.mobile.data.remote.JokesRemoteSource
import com.kurt.jokes.mobile.domain.entities.Joke
import com.kurt.jokes.mobile.domain.repositories.JokesRepository
import com.kurt.jokes.mobile.presentation.base.ioDispatcher
import kotlinx.coroutines.withContext

class JokesRepositoryImpl(
    private val jokesRemoteSource: JokesRemoteSource,
    private val jokesLocalSource: JokesLocalSource
) : JokesRepository {
    override suspend fun getJokes() = withContext(ioDispatcher) {
        try {
            fetchJokes()
        } catch (exception: Exception) {
            println("Repository Error: ${exception.message}")
        }
        jokesLocalSource.getJokes()
    }

    private suspend fun fetchJokes() {
        withContext(ioDispatcher) {
            val jokes = jokesRemoteSource.getJokes()
            jokesLocalSource.saveJokes(jokes)
        }
    }
}