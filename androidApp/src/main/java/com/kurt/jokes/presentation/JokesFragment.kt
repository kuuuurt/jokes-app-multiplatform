package com.kurt.jokes.presentation

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.kurt.jokes.R
import com.kurt.jokes.mobile.presentation.features.jokes.JokesViewModel
import com.kurt.jokes.mobile.presentation.features.jokes.JokesViewModelFactory
import com.kurt.jokes.mobile.presentation.models.UiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.Closeable

@ExperimentalCoroutinesApi
class JokesFragment : Fragment(R.layout.fragment_jokes) {
    private val viewModel: JokesViewModel by viewModels { JokesViewModelFactory() }
    private val jokesAdapter by lazy { JokesListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recJokes = view.findViewById<RecyclerView>(R.id.rec_jokes)

        val loadingJokes = view.findViewById<ProgressBar>(R.id.loading_jokes)
        val emptyJokes = view.findViewById<TextView>(R.id.empty_jokes)

        recJokes.adapter = jokesAdapter

        viewModel.jokesState
            .onEach {
                recJokes.visibility = if (it is UiState.Success) View.VISIBLE else View.INVISIBLE
                loadingJokes.visibility = if (it is UiState.Loading) View.VISIBLE else View.GONE
                emptyJokes.visibility = if (it is UiState.Error) View.VISIBLE else View.GONE
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.jokes
            .onEach { jokesAdapter.submitList(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }
}