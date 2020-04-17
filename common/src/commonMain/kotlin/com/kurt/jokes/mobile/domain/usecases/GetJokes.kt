package com.kurt.jokes.mobile.domain.usecases

import com.kurt.jokes.mobile.domain.repositories.JokesRepository

class GetJokes(private val jokesRepository: JokesRepository) {
    suspend operator fun invoke() = jokesRepository.getJokes()
}