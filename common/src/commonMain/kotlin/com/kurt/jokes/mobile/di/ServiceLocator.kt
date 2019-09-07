package com.kurt.jokes.mobile.di

import com.kurt.jokes.mobile.data.JokesRepositoryImpl
import com.kurt.jokes.mobile.data.engine
import com.kurt.jokes.mobile.data.local.JokesLocalSource
import com.kurt.jokes.mobile.data.remote.JokesRemoteSource
import com.kurt.jokes.mobile.domain.repositories.JokesRepository
import com.kurt.jokes.mobile.domain.usecases.GetJokes

class ServiceLocator {
    val jokesLocalSource = JokesLocalSource()
    val jokesRemoteSource = JokesRemoteSource(engine)
    val jokesRepository: JokesRepository = JokesRepositoryImpl(jokesRemoteSource, jokesLocalSource)

    val getJokes = GetJokes(jokesRepository)
}