package live.neddyap.rutbis.ui.explore.bus

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import live.neddyap.rutbis.data.bus.BusInterface
import live.neddyap.rutbis.data.bus.BusResponse
import live.neddyap.rutbis.data.journey.JourneyInterface
import live.neddyap.rutbis.data.journey.JourneysResponse
import live.neddyap.rutbis.databinding.ActivityBusDetailsBinding
import live.neddyap.rutbis.network.ApiModule

class BusDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBusDetailsBinding
    private val journeyService: JourneyInterface by lazy {
        ApiModule.provideRetrofit().create(JourneyInterface::class.java)
    }
    private val busService: BusInterface by lazy {
        ApiModule.provideRetrofit().create(BusInterface::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.busJourneyList.layoutManager = LinearLayoutManager(this)
        binding.loadingProgressBarDetail.visibility = View.VISIBLE // Show loading

        val busId = intent.getStringExtra("BUS_ID") ?: ""

        CoroutineScope(Dispatchers.Main).launch {
            val journeysResponse = getJourneysForBus(busId)
            val busDetails = getBusDetails(busId)

            if (journeysResponse != null && busDetails != null) {
                Log.i(TAG, "journeysResponse data: $journeysResponse")
                val adapter = BusRouteAdapter(journeysResponse.data)
                binding.busPrice.text = journeysResponse.data[0].price.toString()
//                binding.busTime.text = journeysResponse.data[0].createdAt


                binding.busName.text = busDetails.data.busName
                binding.busLicence.text = busDetails.data.busLicense

                binding.busJourneyList.adapter = adapter
            } else {
                "Error fetching data. Please try again.".showError()
            }
            binding.loadingProgressBarDetail.visibility = View.GONE // Hide loading
        }
    }

    private suspend fun getJourneysForBus(busId: String): JourneysResponse? {
        return withContext(Dispatchers.IO) {
            try {
                journeyService.getJourneysForBus(busId)
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching journeys: ${e.message}")
                null
            }
        }
    }

    private suspend fun getBusDetails(busId: String): BusResponse? {
        return withContext(Dispatchers.IO) {
            try {
                busService.getBus(busId)
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching bus details: ${e.message}")
                null
            }
        }
    }
    private fun String.showError() {
        Toast.makeText(this@BusDetailActivity, this, Toast.LENGTH_SHORT).show()
        Log.e(TAG, this)
    }
    fun back(view: View) {
        finish()
    }
}