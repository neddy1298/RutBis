package live.neddyap.rutbis.ui.explore.bus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import live.neddyap.rutbis.BusDataClass
import live.neddyap.rutbis.R
import live.neddyap.rutbis.databinding.FragmentBusBinding

class BusFragment : Fragment() {

    private var _binding: FragmentBusBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BusAdapterClass

    private lateinit var busDataList: ArrayList<BusDataClass>
    private lateinit var busImageList: ArrayList<Int>
    private lateinit var busTitleList: ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBusBinding.inflate(inflater, container, false)

        busImageList = arrayListOf(R.drawable.ic_bus_colored,R.drawable.ic_bus_colored,R.drawable.ic_bus,R.drawable.ic_bus_colored,R.drawable.ic_bus_colored,R.drawable.ic_bus_colored,)
        busTitleList = arrayListOf("Bus 1", "Bus 2", "Bus 3", "Bus 4", "Bus 5", "Bus 6")
        recyclerView = binding.busRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        busDataList = arrayListOf()
        getBusData()

        adapter = BusAdapterClass(busDataList)
        recyclerView.adapter = adapter

        return binding.root
    }

    private fun getBusData(){
        for (i in busTitleList.indices) {
            val dataClass = BusDataClass(i,busImageList[i] ,busTitleList[i])
            busDataList.add(dataClass)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}