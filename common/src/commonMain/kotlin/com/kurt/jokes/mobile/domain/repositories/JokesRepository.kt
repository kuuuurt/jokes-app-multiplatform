package com.kurt.jokes.mobile.domain.repositories

import com.kurt.jokes.mobile.data.JokesRepositoryImpl
import com.kurt.jokes.mobile.data.local.JokesLocalSource
import com.kurt.jokes.mobile.data.remote.JokesRemoteSource
import com.kurt.jokes.mobile.domain.entities.Joke

interface JokesRepository {
    suspend fun getJokes(): List<Joke>
}