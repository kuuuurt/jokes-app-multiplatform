package com.kurt.jokes.mobile

import com.kurt.jokes.JokesDatabase
import com.kurt.jokes.mobile.data.JokesRepositoryImpl
import com.kurt.jokes.mobile.data.local.JokesDatabaseDriver
import com.kurt.jokes.mobile.data.remote.engine
import com.kurt.jokes.mobile.data.local.JokesLocalSource
import com.kurt.jokes.mobile.data.remote.JokesRemoteSource
import com.kurt.jokes.mobile.domain.repositories.JokesRepository
import com.kurt.jokes.mobile.domain.usecases.GetJokes
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object ServiceLocator {
    // Data
    private val jokesLocalSource = JokesLocalSource(JokesDatabase(JokesDatabaseDriver.getDriver()))
    private val jokesRemoteSource = JokesRemoteSource(engine)
    var jokesRepository: JokesRepository = JokesRepositoryImpl(
        jokesRemoteSource,
        jokesLocalSource
    )

    // Domain
    val getJokes
        get() = GetJokes(jokesRepository)
}