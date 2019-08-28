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
        fetchJokes()
        return jokesLocalSource.getJokes()
    }

    suspend fun fetchJokes() {
        try {
            val jokes = jokesRemoteSource.getJokes()
            jokesLocalSource.saveJokes(jokes)
        } catch(exception: Exception) {
            println("Repository Error")
            println(exception.message)
        }
    }
}