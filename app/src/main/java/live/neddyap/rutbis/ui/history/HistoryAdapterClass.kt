package live.neddyap.rutbis.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import live.neddyap.rutbis.HistoryDataClass
import live.neddyap.rutbis.R

class HistoryAdapterClass(private val dataList: ArrayList<HistoryDataClass>): RecyclerView.Adapter<HistoryAdapterClass.ViewHolderClass>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        holder.rvHistoryDate.text = currentItem.dataHistoryDate
        holder.rvHistoryStart.text = currentItem.dataHistoryStart
        holder.rvHistoryEnd.text = currentItem.dataHistoryEnd
        holder.rvTimeStart.text = currentItem.dataHistoryTimeStart
        holder.rvTimeEnd.text = currentItem.dataHistoryTimeEnd
    }
    override fun getItemCount(): Int {
        return dataList.size
    }


    class ViewHolderClass(itemView: View): RecyclerView.ViewHolder(itemView) {
        val rvHistoryDate: TextView = itemView.findViewById(R.id.history_date)
        val rvHistoryStart: TextView = itemView.findViewById(R.id.history_bus_start)
        val rvHistoryEnd: TextView = itemView.findViewById(R.id.history_bus_end)
        val rvTimeStart: TextView = itemView.findViewById(R.id.history_bus_start_time)
        val rvTimeEnd: TextView = itemView.findViewById(R.id.history_bus_end_time)

    }
}