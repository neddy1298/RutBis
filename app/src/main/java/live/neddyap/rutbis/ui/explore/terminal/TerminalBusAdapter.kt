package live.neddyap.rutbis.ui.explore.terminal

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import live.neddyap.rutbis.R
import live.neddyap.rutbis.data.journey.Journey
import live.neddyap.rutbis.ui.explore.bus.BusDetailActivity

class TerminalBusAdapter(private val journeys: List<Journey>) : RecyclerView.Adapter<TerminalBusAdapter.ViewHolderClass>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_bus, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentJourney = journeys[position]
        val currentBus = currentJourney.busId // Get the Bus object from the Journey
        holder.rvImage.setImageResource(currentBus.busIcon)
        holder.rvTitle.text = currentBus.busName

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, BusDetailActivity::class.java)
            intent.putExtra("BUS_ID", currentBus.busId)
            context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return journeys.size
    }

    class ViewHolderClass(itemView: View): RecyclerView.ViewHolder(itemView) {
        val rvImage : ImageView = itemView.findViewById(R.id.bus_icon)
        val rvTitle : TextView = itemView.findViewById(R.id.bus_text)

    }
}