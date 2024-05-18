package live.neddyap.rutbis.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TabHost
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import live.neddyap.rutbis.BusDataClass
import live.neddyap.rutbis.R
import live.neddyap.rutbis.TerminalDataClass
import live.neddyap.rutbis.databinding.FragmentExploreBinding
import live.neddyap.rutbis.databinding.HeaderBinding

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null
    private lateinit var headerBinding: HeaderBinding
    private val binding get() = _binding!!

    private lateinit var busRecyclerView: RecyclerView
    private lateinit var terminalRecyclerView: RecyclerView
    private lateinit var busDataList: ArrayList<BusDataClass>
    private lateinit var terminalDataList: ArrayList<TerminalDataClass>

    lateinit var busImageList: ArrayList<Int>
    lateinit var terminalImageList: ArrayList<Int>

    lateinit var busTitleList: ArrayList<String>
    lateinit var terminalTitleList: ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val viewModel =
            ViewModelProvider(this)[ExploreViewModel::class.java]
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        headerBinding = HeaderBinding.bind(binding.root)
        val textView: TextView = headerBinding.headerTitle
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        // Initialize TabHost
        val tabHost = binding.exploreTabHost
        tabHost.setup()

        // Create TabSpec for each tab
        var spec: TabHost.TabSpec = tabHost.newTabSpec("Tab1")
        spec.setContent(R.id.busTab)
        spec.setIndicator("Bus")
        tabHost.addTab(spec)

        spec = tabHost.newTabSpec("Tab2")
        spec.setContent(R.id.terminalTab)
        spec.setIndicator("Terminal")
        tabHost.addTab(spec)

//        busImageList = arrayListOf(R.drawable.ic_bus_colored,R.drawable.ic_bus_colored,R.drawable.ic_bus_colored,R.drawable.ic_bus_colored,R.drawable.ic_bus_colored,)
        busTitleList = arrayListOf("Bus 1", "Bus 2", "Bus 3", "Bus 4", "Bus 5", "Bus 6")
        busRecyclerView = binding.busRecyclerView
        busRecyclerView.layoutManager = LinearLayoutManager(context)
        busRecyclerView.setHasFixedSize(true)
        busDataList = arrayListOf()
        getBusData()



//        terminalImageList = arrayListOf(R.drawable.ic_bus_stop,R.drawable.ic_bus_stop,R.drawable.ic_bus_stop,R.drawable.ic_bus_stop,R.drawable.ic_bus_stop,)
        terminalTitleList = arrayListOf("Terminal 1", "Terminal 2", "Terminal 3", "Terminal 4", "Terminal 5", "Terminal 6")
        terminalRecyclerView = binding.terminalRecyclerView
        terminalRecyclerView.layoutManager = LinearLayoutManager(context)
        terminalRecyclerView.setHasFixedSize(true)
        terminalDataList = arrayListOf()
        getTerminalData()

        return root

    }

    private fun getBusData(){
        for (i in busTitleList.indices) {
            val dataClass = BusDataClass(busTitleList[i])
            busDataList.add(dataClass)
        }
        busRecyclerView.adapter = BusAdapterClass(busDataList)
    }

    private fun getTerminalData(){
        for (i in terminalTitleList.indices) {
            val dataClass = TerminalDataClass(terminalTitleList[i])
            terminalDataList.add(dataClass)
        }
        terminalRecyclerView.adapter = TerminalAdapterClass(terminalDataList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}