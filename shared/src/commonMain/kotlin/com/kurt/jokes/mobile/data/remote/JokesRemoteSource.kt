package com.kurt.jokes.mobile.data.remote

import com.kurt.jokes.mobile.domain.entities.Joke
import io.ktor.client.*
import io.ktor.client.request.*

@OptIn(ExperimentalStdlibApi::class)
class JokesRemoteSource(private val client: HttpClient) {
    suspend fun getJokes(): List<Joke> =
        client.get("https://official-joke-api.appspot.com/jokes/ten")
}