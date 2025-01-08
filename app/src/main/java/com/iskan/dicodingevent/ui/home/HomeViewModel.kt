package com.iskan.dicodingevent.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iskan.dicodingevent.R
import com.iskan.dicodingevent.data.response.Event
import kotlinx.coroutines.launch


class HomeViewModel : ViewModel() {


    private val _listCarouselEvents = MutableLiveData<List<Event>>()
    val listCarouselEvent: LiveData<List<Event>> = _listCarouselEvents

    private val _listEvents = MutableLiveData<List<Event>>()
    val listEvent: LiveData<List<Event>> = _listEvents

    private val _isCarouselLoading = MutableLiveData<Boolean>()
    val isCarouselLoading: LiveData<Boolean> = _isCarouselLoading

    private val _isListLoading = MutableLiveData<Boolean>()
    val isListLoading: LiveData<Boolean> = _isListLoading

    companion object {
        const val TAG = "HomeViewModel"
    }

    init {
        findCarouselEvent()
        findListEvent()
    }

    private fun findCarouselEvent() {
        viewModelScope.launch {
            try {
                _isCarouselLoading.value = true
                val response = ApiConfig.getApiService().getEvents(1, 5)
                _listCarouselEvents.value = response.listEvents
            } catch (e: Exception) {
                Log.e(TAG, "onFailure: ${e.message}")
            } finally {
                _isCarouselLoading.value = false
            }
        }
    }

    private fun findListEvent() {
        viewModelScope.launch {
            try {
                _isListLoading.value = true
                val response = ApiConfig.getApiService().getEvents(0, 5)
                _listEvents.value = response.listEvents
            } catch (e: Exception) {
                Log.e(TAG, "onFailure: ${e.message}")
            } finally {
                _isListLoading.value = false
            }
        }
    }

}
