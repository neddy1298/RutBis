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
import live.neddyap.rutbis.data.bus.BusInterface
import live.neddyap.rutbis.data.bus.BusResponse
import live.neddyap.rutbis.data.journey.JourneyInterface
import live.neddyap.rutbis.data.journey.JourneysResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BusDetailActivity : AppCompatActivity() {

    val BASE_URL = "http://10.0.2.2:8080/api/"

    val headerInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("X-Api-Key", "rutbis123")
            .build()
        chain.proceed(request)
    }
    val client = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)
        .build()

    private lateinit var recyclerView: RecyclerView

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

    private suspend fun getBus(busId: String): BusResponse? {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL+"bus/$busId")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BusInterface::class.java)

        return withContext(Dispatchers.IO) {
            try {
                retrofit.getBus(busId)
            } catch (e: Exception) {
                Log.e(ContentValues.TAG, "Error fetching bus details: ${e.message}")
                null // Return null on error
            }
        }
    }

    private suspend fun getJourneysForBus(busId: String): JourneysResponse? {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JourneyInterface::class.java) // Use JourneyInterface here

        return withContext(Dispatchers.IO) {
            try {
                retrofit.getJourneysForBus(busId)
            } catch (e: Exception) {
                Log.e(ContentValues.TAG, "Error fetching journeys: ${e.message}")
                null
            }
        }
    }
    fun onBackPressed(view: View) {
        finish()
    }
}