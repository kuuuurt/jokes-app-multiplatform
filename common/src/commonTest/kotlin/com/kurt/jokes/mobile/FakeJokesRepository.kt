package com.kurt.jokes.mobile

import com.kurt.jokes.mobile.domain.entities.Joke
import com.kurt.jokes.mobile.domain.repositories.JokesRepository

class FakeJokesRepository : JokesRepository {
        override suspend fun getJokes(): List<Joke> {
            return listOf(
                Joke(1, "joke", "joke", "joke")
            )
        }
    }