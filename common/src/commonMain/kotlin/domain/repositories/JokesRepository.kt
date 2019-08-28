package com.kurt.jokes.mobile.domain.repositories

import com.kurt.jokes.mobile.domain.entities.Joke

interface JokesRepository {
    suspend fun getJokes(): List<Joke>
}