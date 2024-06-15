package live.neddyap.rutbis.ui.explore.bus

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import live.neddyap.rutbis.R
import live.neddyap.rutbis.data.journey.Journey
import live.neddyap.rutbis.ui.explore.terminal.TerminalDetailActivity

class BusRouteAdapter(private val journeys: List<Journey>) : RecyclerView.Adapter<BusRouteAdapter.BusRouteViewHolder>() {

    inner class BusRouteViewHolder(itemView: View, private val journeys: List<Journey>) : RecyclerView.ViewHolder(itemView) {
        val terminalName: TextView = itemView.findViewById(R.id.terminal_name)
        val icon: ImageView = itemView.findViewById(R.id.icon)

        init {
            itemView.setOnClickListener {
                val currentJourney = journeys[adapterPosition]
                val intent = Intent(itemView.context, TerminalDetailActivity::class.java)
                intent.putExtra("TERMINAL_ID", currentJourney.terminalId.terminalId)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusRouteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bus_journey, parent, false)
        return BusRouteViewHolder(view, journeys)
    }

    override fun onBindViewHolder(holder: BusRouteViewHolder, position: Int) {
        val journey = journeys[position]
        holder.terminalName.text = journey.terminalId.terminalName
        if (position == journeys.size - 1) {
            holder.icon.setImageResource(R.drawable.ic_bus_stop)
        } else {
            holder.icon.setImageResource(R.drawable.ic_bus_colored)
        }
    }
    override fun getItemCount() = journeys.size
}
