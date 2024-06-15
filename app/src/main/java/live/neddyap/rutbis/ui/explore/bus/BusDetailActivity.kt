package live.neddyap.rutbis.ui.explore.bus

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import live.neddyap.rutbis.R
import live.neddyap.rutbis.data.journey.JourneyInterface
import live.neddyap.rutbis.data.journey.JourneysResponse
import live.neddyap.rutbis.network.ApiModule

class BusDetailActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    private val journeyService: JourneyInterface by lazy {
        ApiModule.provideRetrofit().create(JourneyInterface::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_details)

        recyclerView = findViewById(R.id.bus_journey_list)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val busId = intent.getStringExtra("BUS_ID") ?: ""

        CoroutineScope(Dispatchers.Main).launch {
            val journeysResponse = getJourneysForBus(busId)
            if (journeysResponse != null) {
                val adapter = BusRouteAdapter(journeysResponse.data)
                recyclerView.adapter = adapter
            } else {
                // Handle error fetching journeys
            }
        }
    }

    private suspend fun getJourneysForBus(busId: String): JourneysResponse? {
        return withContext(Dispatchers.IO) {
            try {
                journeyService.getJourneysForBus(busId)
            } catch (e: Exception) {
                Log.e(ContentValues.TAG, "Error fetching journeys: ${e.message}")
                null
            }
        }
    }

    fun back(view: View) {
        finish()
    }
}