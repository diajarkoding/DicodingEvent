package com.iskan.dicodingevent.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iskan.dicodingevent.R

class EventListAdapter(private val events: List<Event>) :
    RecyclerView.Adapter<EventListAdapter.EventListViewHolder>() {

    inner class EventListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val eventImage: ImageView = view.findViewById(R.id.event_image)
        val eventName: TextView = view.findViewById(R.id.event_name)
        val eventDate: TextView = view.findViewById(R.id.event_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_card, parent, false)
        return EventListViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventListViewHolder, position: Int) {
        val event = events[position]
        holder.eventName.text = event.name
        holder.eventDate.text = event.date
        holder.eventImage.setImageResource(event.imageResId)
    }

    override fun getItemCount(): Int = events.size
}
