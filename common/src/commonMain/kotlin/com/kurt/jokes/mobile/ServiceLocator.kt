package com.kurt.jokes.mobile

import com.kurt.jokes.JokesDatabase
import com.kurt.jokes.mobile.data.RealJokesRepository
import com.kurt.jokes.mobile.data.local.JokesDatabaseDriver
import com.kurt.jokes.mobile.data.local.JokesLocalSource
import com.kurt.jokes.mobile.data.remote.JokesRemoteSource
import com.kurt.jokes.mobile.domain.entities.Joke
import com.kurt.jokes.mobile.domain.repositories.JokesRepository
import com.kurt.jokes.mobile.domain.usecases.GetJokes
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlin.native.concurrent.ThreadLocal
import kotlin.reflect.typeOf

@ThreadLocal
object ServiceLocator {
    // Data
    @OptIn(ExperimentalStdlibApi::class)
    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer().apply {
                typeOf<List<Joke>>()
            }
        }
    }
    private val jokesLocalSource = JokesLocalSource(JokesDatabase(JokesDatabaseDriver.getDriver()))
    private val jokesRemoteSource = JokesRemoteSource(client)
    private val jokesRepository: JokesRepository = RealJokesRepository(
        jokesRemoteSource,
        jokesLocalSource
    )

    // Domain
    val getJokes = GetJokes(jokesRepository)
}