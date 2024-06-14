package live.neddyap.rutbis.ui.explore.terminal

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import live.neddyap.rutbis.R
import live.neddyap.rutbis.data.bus.Bus
import live.neddyap.rutbis.ui.explore.bus.BusAdapterClass

class TerminalDetailActivity : AppCompatActivity() {

    private lateinit var terminalBusRecyclerView: RecyclerView

//    val terminalBusList = hashMapOf(
//        0 to arrayListOf(Pair(R.drawable.ic_bus_colored, "Bus1"), Pair(R.drawable.ic_bus_colored, "Bus2"), Pair(R.drawable.ic_bus_colored, "Bus3"), Pair(R.drawable.ic_bus_colored, "Bus4")),
//        1 to arrayListOf(Pair(R.drawable.ic_bus_colored, "Bus3"), Pair(R.drawable.ic_bus_colored, "Bus4"))
//        // Add more terminal bus lists as needed
//    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terminal_details)

        val terminalId = intent.getStringExtra("TERMINAL_ID") ?: ""
//        val busList = terminalBusList[terminalId] ?: arrayListOf()

        terminalBusRecyclerView = findViewById(R.id.terminalBusRecyclerView)
        terminalBusRecyclerView.layoutManager = LinearLayoutManager(this)
        terminalBusRecyclerView.setHasFixedSize(true)

        lifecycleScope.launch {
            val buses = fetchBusesForTerminal(terminalId)
            val adapter = BusAdapterClass(buses)
            terminalBusRecyclerView.adapter = adapter
        }
    }
//        terminalBusRecyclerView = findViewById(R.id.terminalBusRecyclerView)
//        terminalBusRecyclerView.layoutManager = LinearLayoutManager(this)
//        terminalBusRecyclerView.setHasFixedSize(true)
//        terminalBusDataList = arrayListOf()
//        terminalBusRecyclerView.adapter = TerminalBusAdapter(terminalBusDataList)
//        getBusData()

    private suspend fun fetchBusesForTerminal(terminalId: String): List<Bus> {
        // ... Make API call or use repository to fetch data
        // ...
        return emptyList()
    }
//    private fun getBusData(){
//        for (i in busTitleList.indices) {
//            val dataClass = BusDataClass(i,busImageList[i] ,busTitleList[i])
//            terminalBusDataList.add(dataClass)
//        }
//        terminalBusRecyclerView.adapter = BusAdapterClass(terminalBusDataList)
//    }

    fun onBackPressed(view: View) {
        finish()
    }
}