package com.iskan.dicodingevent.ui.home
import EventCarouselAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
            val eventCarouselAdapter = EventCarouselAdapter(events) { event ->
                val action = HomeFragmentDirections.actionHomeFragmentToDetailEventFragment(event.id.toString())
                carouselRecyclerView.findNavController().navigate(action)
            }
            (carouselRecyclerView as RecyclerView).adapter = eventCarouselAdapter
        }

        homeViewModel.listEvent.observe(viewLifecycleOwner) { events ->
            val eventListAdapter = EventListAdapter(events) { event ->
                val action = HomeFragmentDirections.actionHomeFragmentToDetailEventFragment(event.id.toString())
                eventListRecyclerView.findNavController().navigate(action)
            }
            (eventListRecyclerView as RecyclerView).adapter = eventListAdapter
        }

        homeViewModel.isCarouselLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.carouselProgressBar.visibility = View.VISIBLE
                carouselRecyclerView.visibility = View.GONE
            } else {
                binding.carouselProgressBar.visibility = View.GONE
                carouselRecyclerView.visibility = View.VISIBLE
            }
        }

        homeViewModel.isListLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.listProgressBar.visibility = View.VISIBLE
                eventListRecyclerView.visibility = View.GONE
            } else {
                binding.listProgressBar.visibility = View.GONE
                eventListRecyclerView.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
