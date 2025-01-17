import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iskan.dicodingevent.data.response.Event
import com.iskan.dicodingevent.databinding.ItemCarouselCardBinding
import com.iskan.dicodingevent.utils.DateUtils

class EventCarouselAdapter(
    private val events: List<Event>,
    private val onClick: (Event) -> Unit
) :
    RecyclerView.Adapter<EventCarouselAdapter.CarouselViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding =
            ItemCarouselCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val event = events[position]
        holder.bind(event)
        holder.itemView.setOnClickListener {onClick(event)}
    }

    override fun getItemCount(): Int = events.size

    inner class CarouselViewHolder(private val binding: ItemCarouselCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(event: Event) {
            Glide.with(binding.root.context).load(event.mediaCover).into(binding.eventImage)
            binding.eventName.text = event.name
            val eventDate = DateUtils.eventDate(event.beginTime, event.endTime)
            binding.eventDate.text = eventDate

        }
    }
}
