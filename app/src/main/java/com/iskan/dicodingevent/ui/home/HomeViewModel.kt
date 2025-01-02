package com.iskan.dicodingevent.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iskan.dicodingevent.R

// Model data untuk event
data class Event(val name: String, val date: String, val imageResId: Int)

class HomeViewModel : ViewModel() {

    // Daftar event untuk carousel
    private val _events = MutableLiveData<List<Event>>().apply {
        value = listOf(
            Event("Event 1", "2025-01-15", R.drawable.event_be_dicoding),
            Event("Event 2", "2025-01-20", R.drawable.event_be_dicoding),
            Event("Event 3", "2025-01-25", R.drawable.event_be_dicoding),
            Event("Event 3", "2025-01-25", R.drawable.event_be_dicoding),
            Event("Event 3", "2025-01-25", R.drawable.event_be_dicoding)
        )
    }
    val events: LiveData<List<Event>> = _events
}
