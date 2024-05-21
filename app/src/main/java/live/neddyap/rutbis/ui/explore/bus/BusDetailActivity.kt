package live.neddyap.rutbis.ui.explore.bus

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import live.neddyap.rutbis.R

class BusDetailActivity : AppCompatActivity() {

    private val busRoutes = hashMapOf(
        0 to listOf("Ciawi", "Cawang Uki", "Slipi", "Grogol"),
        1 to listOf("Ciawi", "Cawang Uki", "Tanah Abang")
        // Add more bus routes as needed
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bus_details)

        val busId = intent.getIntExtra("BUS_ID", -1)
        Log.d(TAG, "onCreate: busId: $busId")
        val stops = busRoutes[busId] ?: listOf("kosong") // Use an empty list as a default value

        val recyclerView: RecyclerView = findViewById(R.id.bus_journey_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = BusRouteAdapter(stops)
    }
}