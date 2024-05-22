package live.neddyap.rutbis.ui.explore.terminal

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import live.neddyap.rutbis.BusDataClass
import live.neddyap.rutbis.R
import live.neddyap.rutbis.ui.explore.BusAdapterClass

class TerminalDetailActivity : AppCompatActivity() {

    private lateinit var terminalBusRecyclerView: RecyclerView
    private lateinit var terminalBusDataList: ArrayList<BusDataClass>

    lateinit var busImageList: ArrayList<Int>
    lateinit var busTitleList: ArrayList<String>

    val terminalBusList = hashMapOf(
        0 to arrayListOf(Pair(R.drawable.ic_bus, "Bus1"), Pair(R.drawable.ic_bus, "Bus2")),
        1 to arrayListOf(Pair(R.drawable.ic_bus, "Bus3"), Pair(R.drawable.ic_bus, "Bus4"))
        // Add more terminal bus lists as needed
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terminal_details)

//        val terminalId = intent.getIntExtra("TERMINAL_ID", -1)
//        val stops = busList[terminalId] ?: listOf("kosong") // Use an empty list as a default value

        val terminalId = intent.getIntExtra("TERMINAL_ID", -1)
        val busList = terminalBusList[terminalId] ?: arrayListOf()

        busTitleList = ArrayList(busList.map { it.second }) // Extract bus titles
        busImageList = ArrayList(busList.map { it.first }) // Extract bus images

        terminalBusRecyclerView = findViewById(R.id.terminalBusRecyclerView)
        terminalBusRecyclerView.layoutManager = LinearLayoutManager(this)
        terminalBusRecyclerView.setHasFixedSize(true)
        terminalBusDataList = arrayListOf()
        terminalBusRecyclerView.adapter = TerminalBusAdapterClass(terminalBusDataList)
        getBusData()

    }
    private fun getBusData(){
        for (i in busTitleList.indices) {
            val dataClass = BusDataClass(i,busImageList[i] ,busTitleList[i])
            terminalBusDataList.add(dataClass)
        }
        terminalBusRecyclerView.adapter = BusAdapterClass(terminalBusDataList)
    }

    fun onBackPressed(view: View) {
        finish()
    }
}