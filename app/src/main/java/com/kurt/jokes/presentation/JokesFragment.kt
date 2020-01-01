package com.kurt.jokes.presentation

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.kurt.jokes.R
import com.kurt.jokes.mobile.di.ServiceLocator

class JokesFragment : Fragment(R.layout.fragment_jokes) {
    private val viewModel: JokesViewModel by viewModels {
        JokesViewModelFactory(ServiceLocator.getJokes)
    }
    private val jokesAdapter by lazy { JokesListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recJokes = view.findViewById<RecyclerView>(R.id.rec_jokes)

        val loadingJokes = view.findViewById<ProgressBar>(R.id.loading_jokes)
        val emptyJokes = view.findViewById<TextView>(R.id.empty_jokes)

        recJokes.adapter = jokesAdapter

        viewModel.jokesState.observe(viewLifecycleOwner, Observer {
            recJokes.visibility = if (it is UiState.Success) View.VISIBLE else View.INVISIBLE
            loadingJokes.visibility = if (it is UiState.Loading) View.VISIBLE else View.GONE
            emptyJokes.visibility = if (it is UiState.Error) View.VISIBLE else View.GONE

            if (it is UiState.Success) {
                jokesAdapter.submitList(it.data)
            }
        })
    }
}