package com.iskan.dicodingevent.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.iskan.dicodingevent.databinding.FragmentSearchBinding
import com.iskan.dicodingevent.ui.adapter.EventListAdapter

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var searchViewModel: SearchViewModel
    private var toastShown = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        setupRecyclerView()
        setupSearchView()
        observeViewModel()

        return root
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.searchRecyclerview
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    searchViewModel.searchEvents(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun observeViewModel() {
        searchViewModel.searchResults.observe(viewLifecycleOwner) { events ->
            if (events.isEmpty() && binding.searchView.query.isNotEmpty()) {
                if (!toastShown) {
                    Toast.makeText(context, "No events found", Toast.LENGTH_SHORT).show()
                    toastShown = true
                }
            } else {
                toastShown = false
                val adapter = EventListAdapter(events) { event ->
                    val action = SearchFragmentDirections.actionSearchFragmentToDetailEventFragment(event.id.toString())
                    binding.searchRecyclerview.findNavController().navigate(action)
                }
                binding.searchRecyclerview.adapter = adapter
            }
        }

        searchViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.searchRecyclerview.visibility = if (isLoading) View.GONE else View.VISIBLE
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        resetSearchView()
    }

    private fun resetSearchView() {
        binding.searchView.setQuery("", false)
        binding.searchView.clearFocus()
        binding.searchRecyclerview.adapter = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
