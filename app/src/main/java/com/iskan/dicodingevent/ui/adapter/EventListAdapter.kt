package com.iskan.dicodingevent.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iskan.dicodingevent.data.response.Event
import com.iskan.dicodingevent.databinding.ItemListCardBinding
import com.iskan.dicodingevent.utils.DateUtils

class EventListAdapter(private val events: List<Event>) :
    RecyclerView.Adapter<EventListAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemListCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val event = events[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int = events.size

    inner class ListViewHolder(private val binding: ItemListCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(event: Event) {
            Glide.with(binding.root.context).load(event.mediaCover).into(binding.eventImage)
            binding.eventName.text = event.name
            val eventDate = DateUtils.eventDate(event.beginTime, event.endTime)
            binding.eventDate.text = eventDate
        }
    }
}

