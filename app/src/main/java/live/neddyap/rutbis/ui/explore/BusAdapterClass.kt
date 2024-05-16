package live.neddyap.rutbis.ui.explore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import live.neddyap.rutbis.R

class BusAdapterClass(private val dataList: ArrayList<BusDataClass>): RecyclerView.Adapter<BusAdapterClass.ViewHolderClass>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_bus, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
//        holder.rvImage.setBackgroundResource(currentItem.dataImage)
        holder.rvTitle.text = currentItem.dataTitle
    }
    override fun getItemCount(): Int {
        return dataList.size
    }


    class ViewHolderClass(itemView: View): RecyclerView.ViewHolder(itemView) {
//        val rvImage : TextView = itemView.findViewById(R.id.bus_icon)
        val rvTitle : TextView = itemView.findViewById(R.id.bus_text)

    }
}