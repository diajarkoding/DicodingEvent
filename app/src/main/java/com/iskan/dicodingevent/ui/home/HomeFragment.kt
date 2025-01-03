package com.iskan.dicodingevent.ui.home
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.iskan.dicodingevent.databinding.FragmentHomeBinding
import com.iskan.dicodingevent.ui.adapter.EventListAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val carouselRecyclerView = binding.carouselRecyclerview
        carouselRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val eventListRecyclerView = binding.eventListRecyclerview
        eventListRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        observeViewModel(carouselRecyclerView, eventListRecyclerView)

        return root
    }

    private fun observeViewModel(carouselRecyclerView: View, eventListRecyclerView: View) {
        homeViewModel.listCarouselEvent.observe(viewLifecycleOwner) { events ->
            val eventCarouselAdapter = EventCarouselAdapter(events)
            (carouselRecyclerView as androidx.recyclerview.widget.RecyclerView).adapter =
                eventCarouselAdapter
        }

        homeViewModel.listEvent.observe(viewLifecycleOwner) { events ->
            val eventListAdapter = EventListAdapter(events)
            (eventListRecyclerView as androidx.recyclerview.widget.RecyclerView).adapter =
                eventListAdapter
        }

        homeViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
                carouselRecyclerView.visibility = View.GONE
                eventListRecyclerView.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
                carouselRecyclerView.visibility = View.VISIBLE
                eventListRecyclerView.visibility = View.VISIBLE
            }
        }

        homeViewModel.listCarouselEvent.observe(viewLifecycleOwner) { events ->
            if (events.isEmpty()) {
                Toast.makeText(context, "No upcoming events available", Toast.LENGTH_SHORT).show()
            }
        }

        homeViewModel.listEvent.observe(viewLifecycleOwner) { events ->
            if (events.isEmpty()) {
                Toast.makeText(context, "No finished events available", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
