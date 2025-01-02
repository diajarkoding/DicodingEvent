package com.iskan.dicodingevent.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iskan.dicodingevent.R
import com.iskan.dicodingevent.data.response.Event
import com.iskan.dicodingevent.databinding.ItemCarouselCardBinding

class EventCarouselAdapter(private val events: List<Event>) :
    RecyclerView.Adapter<EventCarouselAdapter.CarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding =
            ItemCarouselCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val event = events[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int = events.size

    inner class CarouselViewHolder(private val binding: ItemCarouselCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(event: Event) {
            Glide.with(binding.root.context).load(event.mediaCover).into(binding.eventImage)
            binding.eventName.text = event.name
            "${event.beginTime} - ${event.endTime}".also { binding.eventDate.text = it }
        }
    }
}
