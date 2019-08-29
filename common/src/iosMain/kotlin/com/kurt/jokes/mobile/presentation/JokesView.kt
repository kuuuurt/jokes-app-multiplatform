package presentation

import com.kurt.jokes.mobile.domain.entities.Joke

interface JokesView {
    fun showJokes(jokes: List<Joke>)
}