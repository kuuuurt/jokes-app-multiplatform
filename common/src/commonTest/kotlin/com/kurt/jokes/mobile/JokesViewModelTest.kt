package com.kurt.jokes.mobile

import com.kurt.jokes.mobile.domain.usecases.GetJokes
import com.kurt.jokes.mobile.presentation.base.mainDispatcher
import com.kurt.jokes.mobile.presentation.features.jokes.JokesViewModel
import kotlinx.coroutines.flow.first
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class JokesViewModelTest : CommonTest() {
    private lateinit var viewModel: JokesViewModel

    @BeforeTest
    fun coroutinesSetup() {
        mainDispatcher = CommonTestCoroutineDispatcher()
    }

    private fun setup() {
        viewModel = JokesViewModel(GetJokes(FakeJokesRepository()))
    }

    @Test
    fun `should get jokes on start`() = runBlocking {
        // When - viewModel is created
        setup()

        // Then - it should have the list of jokes
        val jokes = viewModel.jokes.first()
        assertEquals(1, jokes.size)

        val firstJoke = jokes[0]
        assertEquals(1, firstJoke.id)
        assertEquals("joke", firstJoke.type)
        assertEquals("joke", firstJoke.setup)
        assertEquals("joke", firstJoke.punchline)
    }
}