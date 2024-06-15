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
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TerminalDetailActivity : AppCompatActivity() {

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
            val journeysResponse = getJourneysForTerminal(terminalId)
            if (journeysResponse != null) {
                val adapter = TerminalBusAdapter(journeysResponse.data) // Pass journeysResponse.data directly
                terminalBusRecyclerView.adapter = adapter
            } else {
                // Handle error fetching journeys
            }
        }
    }

    fun onBackPressed(view: View) {
        finish()
    }

    private suspend fun getJourneysForTerminal(terminalId: String): JourneysResponse? {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JourneyInterface::class.java)

        return withContext(Dispatchers.IO) {
            try {
                retrofit.getJourneysForTerminal(terminalId)
            } catch (e: Exception) {
                Log.e(ContentValues.TAG, "Error fetching journeys: ${e.message}")
                null
            }
        }
    }
}