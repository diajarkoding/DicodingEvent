package com.iskan.dicodingevent.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iskan.dicodingevent.R

class EventCarouselAdapter(private val events: List<Event>) :
    RecyclerView.Adapter<EventCarouselAdapter.EventViewHolder>() {

    inner class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val eventImage: ImageView = view.findViewById(R.id.event_image)
        val eventName: TextView = view.findViewById(R.id.event_name)
        val eventDate: TextView = view.findViewById(R.id.event_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_carousel_card, parent, false)

        // Set lebar item menjadi sama dengan layar (dengan margin dihitung)
        val layoutParams = view.layoutParams
        layoutParams.width = (parent.measuredWidth * 0.9).toInt() // 90% dari lebar layar
        view.layoutParams = layoutParams

        return EventViewHolder(view)
    }


    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.eventName.text = event.name
        holder.eventDate.text = event.date
        holder.eventImage.setImageResource(event.imageResId)
    }

    override fun getItemCount(): Int = events.size
}
