package com.kurt.jokes.jokes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.kurt.jokes.jokes.R

class JokesFragment : Fragment() {
    private val viewModel: JokesViewModel by viewModels { JokesViewModelFactory() }

    private val jokesAdapter by lazy { JokesListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(requireContext()).inflate(R.layout.fragment_jokes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recJokes = view.findViewById<RecyclerView>(R.id.rec_jokes)

        recJokes.adapter = jokesAdapter

        viewModel.jokes.observe(viewLifecycleOwner, Observer {
            jokesAdapter.submitList(it)
        })
    }
}