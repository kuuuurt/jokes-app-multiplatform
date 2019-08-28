package com.kurt.jokes.mobile.data.remote

import com.kurt.jokes.mobile.domain.entities.Joke
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.serialization.list

class JokesRemoteSource(clientEngine: HttpClientEngine) {
    private val client = HttpClient(clientEngine) {
        install(JsonFeature) {
            serializer = KotlinxSerializer().apply {
                register(Joke.serializer().list)
            }
        }
    }

    suspend fun getJokes(): List<Joke> =
        client.get("https://official-joke-api.appspot.com/jokes/ten")

}