package com.kurt.jokes.mobile.domain.usecases

import com.kurt.jokes.mobile.data.JokesRepositoryImpl
import com.kurt.jokes.mobile.data.engine
import com.kurt.jokes.mobile.data.local.JokesLocalSource
import com.kurt.jokes.mobile.data.remote.JokesRemoteSource
import com.kurt.jokes.mobile.domain.repositories.JokesRepository

class GetJokes(private val jokesRepository: JokesRepository) {
    suspend operator fun invoke() = jokesRepository.getJokes()

    companion object {
        fun create() = GetJokes(JokesRepositoryImpl(JokesRemoteSource(engine), JokesLocalSource()))
    }
}