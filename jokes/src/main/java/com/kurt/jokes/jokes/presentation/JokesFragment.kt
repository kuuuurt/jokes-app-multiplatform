package com.kurt.jokes.jokes.presentation

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.kurt.jokes.commonandroid.presentation.UiState
import com.kurt.jokes.jokes.R

class JokesFragment : Fragment(R.layout.fragment_jokes) {
    private val viewModel: JokesViewModel by viewModels { JokesViewModelFactory() }
    private val jokesAdapter by lazy { JokesListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recJokes = view.findViewById<RecyclerView>(R.id.rec_jokes)

        val swpJokes = view.findViewById<SwipeRefreshLayout>(R.id.swp_jokes)
        val loadingJokes = view.findViewById<ProgressBar>(R.id.loading_jokes)
        val emptyJokes = view.findViewById<TextView>(R.id.empty_jokes)

        recJokes.adapter = jokesAdapter

        viewModel.jokesState.observe(viewLifecycleOwner, Observer {
            swpJokes.visibility = if (it is UiState.Success) View.VISIBLE else View.INVISIBLE
            loadingJokes.visibility = if (it is UiState.Loading) View.VISIBLE else View.GONE
            emptyJokes.visibility = if (it is UiState.Error) View.VISIBLE else View.GONE

            if (it is UiState.Success) {
                jokesAdapter.submitList(it.data)
            }
        })
    }
}