package live.neddyap.rutbis.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import live.neddyap.rutbis.HistoryDataClass
import live.neddyap.rutbis.databinding.FragmentHistoryBinding
import live.neddyap.rutbis.ui.history.HistoryAdapterClass

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null

    private lateinit var historyRecyclerView: RecyclerView
    private lateinit var historyDataList: ArrayList<HistoryDataClass>

    lateinit var historyDateList: ArrayList<String>
    lateinit var historyBusStartList: ArrayList<String>
    lateinit var historyBusEndList: ArrayList<String>
    lateinit var historyTimeStartList: ArrayList<String>
    lateinit var historyTimeEndList: ArrayList<String>

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        historyDateList = arrayListOf("20/2/2024", "12/5/2024", "12/1/2024", "5/7/2024", "5/7/2024", "5/7/2024")
        historyBusStartList = arrayListOf("Bus 1", "Bus 2", "Bus 3", "Bus 4", "Bus 5", "Bus 6")
        historyBusEndList = arrayListOf("Bus 1", "Bus 2", "Bus 3", "Bus 4", "Bus 5", "Bus 6")
        historyTimeStartList = arrayListOf("Bus 1", "Bus 2", "Bus 3", "Bus 4", "Bus 5", "Bus 6")
        historyTimeEndList = arrayListOf("Bus 1", "Bus 2", "Bus 3", "Bus 4", "Bus 5", "Bus 6")
        historyRecyclerView = binding.activityRecyclerView
        historyRecyclerView.layoutManager = LinearLayoutManager(context)
        historyRecyclerView.setHasFixedSize(true)
        historyDataList = arrayListOf()
        getHistoryData()

        return root

    }

    private fun getHistoryData(){
        for (i in historyDateList.indices) {
            val dataClass = HistoryDataClass(historyDateList[i], historyBusStartList[i], historyBusEndList[i], historyTimeStartList[i], historyTimeEndList[i])
            historyDataList.add(dataClass)
        }
        historyRecyclerView.adapter = HistoryAdapterClass(historyDataList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}