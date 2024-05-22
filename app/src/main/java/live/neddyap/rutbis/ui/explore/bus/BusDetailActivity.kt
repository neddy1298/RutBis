package live.neddyap.rutbis.ui.explore.bus

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import live.neddyap.rutbis.R

class BusDetailActivity : AppCompatActivity() {

    private val busRoutes = hashMapOf(
        0 to listOf("Ciawi", "Cawang Uki", "Slipi", "Grogol"),
        1 to listOf("Ciawi", "Cawang Uki", "Tanah Abang")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_details)

        val busId = intent.getIntExtra("BUS_ID", -1)
        val stops = busRoutes[busId] ?: listOf("kosong") // Use an empty list as a default value

        val recyclerView: RecyclerView = findViewById(R.id.bus_journey_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = BusRouteAdapter(stops)
    }

    fun onBackPressed(view: View) {
        finish()
    }
}