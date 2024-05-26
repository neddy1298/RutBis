//package live.neddyap.rutbis.ui.search
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import live.neddyap.rutbis.BusDataClass
//import live.neddyap.rutbis.R
//import live.neddyap.rutbis.TerminalDataClass
//
//class SearchAdapter(val busList: List<BusDataClass>, val terminalList: List<TerminalDataClass>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    private val busType = 0
//    private val terminalType = 1
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return if (viewType == busType) {
//            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bus, parent, false)
//            BusViewHolder(view)
//        } else {
//            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_terminal, parent, false)
//            TerminalViewHolder(view)
//        }
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        if (holder is BusViewHolder) {
//            holder.bind(busList[position])
//        } else if (holder is TerminalViewHolder) {
//            holder.bind(terminalList[position - busList.size])
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return busList.size + terminalList.size
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return if (position < busList.size) {
//            busType
//        } else {
//            terminalType
//        }
//    }
//
//    class BusViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        private val busName: TextView = itemView.findViewById(R.id.busName)
//        private val busNumber: TextView = itemView.findViewById(R.id.busNumber)
//
//        fun bind(bus: BusDataClass) {
//            busName.text = bus.name
//            busNumber.text = bus.number
//        }
//    }
//
//    class TerminalViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        private val terminalName: TextView = itemView.findViewById(R.id.terminalName)
//        private val terminalLocation: TextView = itemView.findViewById(R.id.terminalLocation)
//
//        fun bind(terminal: TerminalDataClass) {
//            terminalName.text = terminal.name
//            terminalLocation.text = terminal.location
//        }
//    }
//}