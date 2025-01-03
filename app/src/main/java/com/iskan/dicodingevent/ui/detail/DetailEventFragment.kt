package com.iskan.dicodingevent.ui.detail

import DetailEventViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.iskan.dicodingevent.databinding.FragmentDetailEventBinding

class DetailEventFragment : Fragment() {
    private var _binding: FragmentDetailEventBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val detailEventViewModel =
            ViewModelProvider(this).get(DetailEventViewModel::class.java)

        _binding = FragmentDetailEventBinding.inflate(inflater, container, false)
        val root: View = binding.root

        detailEventViewModel.text.observe(viewLifecycleOwner) { text ->
            binding.textDetailEvent.text = text
        }

        return root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                findNavController().navigateUp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
