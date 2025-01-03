package com.iskan.dicodingevent.ui.detail

import DetailEventViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.iskan.dicodingevent.databinding.FragmentDetailEventBinding

class DetailEventFragment : Fragment() {
    private var _binding: FragmentDetailEventBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val detailEventViewModel =
            ViewModelProvider(this).get(DetailEventViewModel::class.java)

        _binding = FragmentDetailEventBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDetailEvent
        detailEventViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}