package com.iskan.dicodingevent.ui.detail

import DetailEventViewModel
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.iskan.dicodingevent.databinding.FragmentDetailEventBinding
import com.iskan.dicodingevent.utils.DateUtils

class DetailEventFragment : Fragment() {
    private var _binding: FragmentDetailEventBinding? = null
    private val binding get() = _binding!!
    private lateinit var detailEventViewModel: DetailEventViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailEventBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val eventId = arguments?.let {
            DetailEventFragmentArgs.fromBundle(it).eventId
        }

        detailEventViewModel = ViewModelProvider(this).get(DetailEventViewModel::class.java)

        detailEventViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.detailProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.eventDetailLayout.visibility = if (isLoading) View.GONE else View.VISIBLE
            binding.webViewEventDescription.visibility = if (isLoading) View.GONE else View.VISIBLE
        }

        detailEventViewModel.listEvent.observe(viewLifecycleOwner) { event ->
            Glide.with(binding.root.context).load(event.mediaCover).into(binding.eventImage)
            binding.eventTitle.text = event.name
            binding.eventOrganizer.text = event.ownerName
            val eventDate = DateUtils.eventDate(event.beginTime, event.endTime)
            binding.eventTime.text = eventDate
            binding.eventCategory.text = event.category
            event.quota.toString().also { binding.eventQuota.text = it }



            fun adjustImageSizeInHtml(htmlContent: String): String {
                val css = """
        <style>
            img {
                max-width: 100%;
                height: auto;
            }
        </style>
    """
                return css + htmlContent
            }

            val eventDescriptionHtml = event.description
            val modifiedHtml = adjustImageSizeInHtml(eventDescriptionHtml)
            binding.webViewEventDescription.loadDataWithBaseURL(
                null,
                modifiedHtml,
                "text/html",
                "UTF-8",
                null
            )

            binding.buttonMoreInfo.setOnClickListener {
                val link = event.link
                if (link.isNotEmpty()) {
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse(link)
                    }
                    startActivity(intent)
                } else {
                    Toast.makeText(requireContext(), "Link tidak tersedia", Toast.LENGTH_SHORT).show()
                }
            }
        }



        eventId?.let { detailEventViewModel.getEventDetail(it) }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
