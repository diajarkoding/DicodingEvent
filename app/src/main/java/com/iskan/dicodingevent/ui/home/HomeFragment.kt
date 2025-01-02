package com.iskan.dicodingevent.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.iskan.dicodingevent.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Initialize ViewModel
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Set up RecyclerView for carousel
        val carouselRecyclerView = binding.carouselRecyclerview
        carouselRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        // Set up RecyclerView for event list
        val eventListRecyclerView = binding.eventListRecyclerview
        eventListRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        // Observe events data from ViewModel
        homeViewModel.events.observe(viewLifecycleOwner) { events ->
            // Set adapter for carousel
            val eventCarouselAdapter = EventCarouselAdapter(events)
            carouselRecyclerView.adapter = eventCarouselAdapter

            // Set adapter for event list
            val eventListAdapter = EventListAdapter(events)
            eventListRecyclerView.adapter = eventListAdapter
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
