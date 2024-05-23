package live.neddyap.rutbis.ui.explore.terminal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import live.neddyap.rutbis.R
import live.neddyap.rutbis.TerminalDataClass
import live.neddyap.rutbis.databinding.FragmentTerminalBinding

class TerminalFragment : Fragment() {

    private var _binding: FragmentTerminalBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TerminalAdapterClass

    private lateinit var terminalDataList: ArrayList<TerminalDataClass>
    private lateinit var terminalImageList: ArrayList<Int>
    private lateinit var terminalTitleList: ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTerminalBinding.inflate(inflater, container, false)

        terminalImageList = arrayListOf(R.drawable.ic_bus_stop,R.drawable.ic_bus_stop,R.drawable.ic_bus_stop,R.drawable.bus_terminal,R.drawable.ic_bus_stop,R.drawable.ic_bus_stop,)
        terminalTitleList = arrayListOf("Terminal 1", "Terminal 2", "Terminal 3", "Terminal 4", "Terminal 5", "Terminal 6")
        recyclerView = binding.terminalRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        terminalDataList = arrayListOf()
        getTerminalData()

        adapter = TerminalAdapterClass(terminalDataList)
        recyclerView.adapter = adapter

        return binding.root
    }
    private fun getTerminalData(){
        for (i in terminalTitleList.indices) {
            val dataClass = TerminalDataClass(i,terminalImageList[i],terminalTitleList[i])
            terminalDataList.add(dataClass)
        }
        recyclerView.adapter = TerminalAdapterClass(terminalDataList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}