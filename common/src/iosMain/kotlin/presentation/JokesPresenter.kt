package com.kurt.jokes.mobile.presentation

import com.kurt.jokes.mobile.domain.defaultDispatcher
import com.kurt.jokes.mobile.domain.usecases.GetJokes
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import presentation.JokesView

class JokesPresenter(
    private val jokesView: JokesView,
    private val getJokes: GetJokes
) {
    val presenterJob = SupervisorJob()
    val presenterScope: CoroutineScope = CoroutineScope(defaultDispatcher + presenterJob)



    fun getJokes() {
        presenterScope.launch(CoroutineExceptionHandler { _, throwable ->
            println("Presenter Error")
        }) {
            val jokes = getJokes.invoke()
            jokesView.showJokes(jokes)
        }
    }
}