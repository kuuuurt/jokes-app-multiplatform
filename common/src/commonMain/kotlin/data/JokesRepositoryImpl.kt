package com.kurt.jokes.mobile.data

import com.kurt.jokes.mobile.data.local.JokesLocalSource
import com.kurt.jokes.mobile.data.remote.JokesRemoteSource
import com.kurt.jokes.mobile.domain.entities.Joke
import com.kurt.jokes.mobile.domain.repositories.JokesRepository

class JokesRepositoryImpl(
    private val jokesRemoteSource: JokesRemoteSource,
    private val jokesLocalSource: JokesLocalSource
) : JokesRepository {
    override suspend fun getJokes(): List<Joke> {
        try {
            fetchJokes()
        } finally {
            return jokesLocalSource.getJokes()
        }
    }

    private suspend fun fetchJokes() {
        val jokes = jokesRemoteSource.getJokes()
        jokesLocalSource.saveJokes(jokes)
    }
}