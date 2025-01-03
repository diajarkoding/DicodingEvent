import com.iskan.dicodingevent.data.response.DetailEventResponse
import com.iskan.dicodingevent.data.response.EventResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("events")
    suspend fun getEvents(
        @Query("active") active: Int = 1,
        @Query("limit") limit: Int = 40,
        @Query("q") query: String? = null
    ): EventResponse

    @GET("events/{id}")
    suspend fun getEventDetail(
        @Path("id") id: String
    ): DetailEventResponse
}