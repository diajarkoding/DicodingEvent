package com.iskan.dicodingevent.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iskan.dicodingevent.data.response.Event
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _searchResults = MutableLiveData<List<Event>>()
    val searchResults: LiveData<List<Event>> = _searchResults

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        const val TAG = "SearchViewModel"
    }

    fun searchEvents(query: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = ApiConfig.getApiService().getEvents(query = query)
                _searchResults.value = response.listEvents
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching events: ${e.message}")
                _searchResults.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
