package live.neddyap.rutbis.ui.explore.terminal

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import live.neddyap.rutbis.R
import live.neddyap.rutbis.data.journey.JourneyInterface
import live.neddyap.rutbis.data.journey.JourneysResponse
import live.neddyap.rutbis.network.ApiModule

class TerminalDetailActivity : AppCompatActivity() {

    private lateinit var terminalBusRecyclerView: RecyclerView

    private val journeyService: JourneyInterface by lazy {
        ApiModule.provideRetrofit().create(JourneyInterface::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terminal_details)

        val terminalId = intent.getStringExtra("TERMINAL_ID") ?: ""

        terminalBusRecyclerView = findViewById(R.id.terminalBusRecyclerView)
        terminalBusRecyclerView.layoutManager = LinearLayoutManager(this)
        terminalBusRecyclerView.setHasFixedSize(true)

        lifecycleScope.launch {
            val journeysResponse = getJourneysForTerminal(terminalId)
            if (journeysResponse != null) {
                val adapter = TerminalBusAdapter(journeysResponse.data)
                terminalBusRecyclerView.adapter = adapter
            } else {
                // Handle error fetching journeys
            }
        }
    }

    fun onBackPressed(view: View) {
        finish()
    }

    private suspend fun getJourneysForTerminal(busId: String): JourneysResponse? {
        return withContext(Dispatchers.IO) {
            try {
                journeyService.getJourneysForTerminal(busId)
            } catch (e: Exception) {
                Log.e(ContentValues.TAG, "Error fetching journeys: ${e.message}")
                null
            }
        }
    }
}