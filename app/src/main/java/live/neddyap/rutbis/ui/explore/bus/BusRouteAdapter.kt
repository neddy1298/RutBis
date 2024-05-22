package live.neddyap.rutbis.ui.explore.bus

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import live.neddyap.rutbis.R
import live.neddyap.rutbis.ui.explore.terminal.TerminalDetailActivity

class BusRouteAdapter(private val stops: List<String>) : RecyclerView.Adapter<BusRouteAdapter.BusRouteViewHolder>() {

    inner class BusRouteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val stopName: TextView = itemView.findViewById(R.id.stop_name)
        val icon: ImageView = itemView.findViewById(R.id.icon)

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, TerminalDetailActivity::class.java)
                intent.putExtra("TERMINAL_ID", adapterPosition)
                itemView.context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusRouteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bus_journey, parent, false)
        return BusRouteViewHolder(view)
    }

    override fun onBindViewHolder(holder: BusRouteViewHolder, position: Int) {
        val stop = stops[position]
        holder.stopName.text = stop
        if (position == stops.size - 1) {
            holder.icon.setImageResource(R.drawable.ic_bus_stop)
        } else {
            holder.icon.setImageResource(R.drawable.ic_bus_colored)
        }
    }

    override fun getItemCount() = stops.size
}
