import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iskan.dicodingevent.data.response.Event
import com.iskan.dicodingevent.ui.home.HomeViewModel
import com.iskan.dicodingevent.ui.home.HomeViewModel.Companion
import kotlinx.coroutines.launch

class DetailEventViewModel : ViewModel() {
    private val _detailEvent = MutableLiveData<Event>()
    val listEvent: LiveData<Event> = _detailEvent

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        const val TAG = "DetailEventViewModel"
    }

    fun getEventDetail(eventId: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = ApiConfig.getApiService().getEventDetail(eventId)
                _detailEvent.value = response.event
            } catch (e: Exception) {
                Log.e(TAG, "onFailure: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}
