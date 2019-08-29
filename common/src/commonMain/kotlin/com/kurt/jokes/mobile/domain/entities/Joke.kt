package com.kurt.jokes.mobile.domain.entities

import kotlinx.serialization.Serializable

@Serializable
data class Joke(
    val id: Long,
    val type: String,
    val setup: String,
    val punchline: String
)