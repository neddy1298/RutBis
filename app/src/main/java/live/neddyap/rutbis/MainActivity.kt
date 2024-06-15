package live.neddyap.rutbis

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import live.neddyap.rutbis.data.bus.Bus
import live.neddyap.rutbis.data.bus.BusInterface
import live.neddyap.rutbis.data.terminal.Terminal
import live.neddyap.rutbis.data.terminal.TerminalInterface
import live.neddyap.rutbis.databinding.ActivityMainBinding
import live.neddyap.rutbis.network.ApiModule
import live.neddyap.rutbis.ui.explore.ExploreFragment
import live.neddyap.rutbis.ui.favorites.FavoritesFragment
import live.neddyap.rutbis.ui.home.HomeFragment
import live.neddyap.rutbis.ui.settings.SettingsFragment

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var bottomNavigationView: BottomNavigationView

    lateinit var binding: ActivityMainBinding

    private var mLocationPermissionGranted = false
    private lateinit var mMap: GoogleMap

    private val busService: BusInterface by lazy {
        ApiModule.provideRetrofit().create(BusInterface::class.java)
    }

    private val terminalService: TerminalInterface by lazy {
        ApiModule.provideRetrofit().create(TerminalInterface::class.java)
    }
    @DelicateCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_home -> {
                    replaceFragment(HomeFragment())
                }

                R.id.bottom_promo -> {
                    replaceFragment(live.neddyap.rutbis.ui.history.HistoryFragment())
                }

                R.id.bottom_favorite -> {
                    replaceFragment(FavoritesFragment())
                }

                R.id.bottom_explore -> {
                    GlobalScope.launch {
                        val busesResponse = getBuses()
                        val terminalsResponse = getTerminals()
                        replaceFragment(ExploreFragment(), busesResponse, terminalsResponse)
                    }
                    replaceFragment(ExploreFragment())
                }

                R.id.bottom_settings -> {
                    replaceFragment(SettingsFragment())
                }
            }
            true
        }
        replaceFragment(HomeFragment())
    }

    private fun replaceFragment(
        fragment: androidx.fragment.app.Fragment,
        busData: List<Bus>? = null,
        terminalData: List<Terminal>? = null
    ) {
        val bundle = Bundle()
        busData?.let {
            bundle.putParcelableArrayList("buses", ArrayList(it))
        }
        terminalData?.let {
            bundle.putParcelableArrayList("terminals", ArrayList(it))
        }
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    private suspend fun getBuses(): List<Bus>? {
        return withContext(Dispatchers.IO) {
            try {
                val busesResponse = busService.getBuses()
                busesResponse.data
            } catch (e: Exception) {
                Log.e(ContentValues.TAG, "Error fetching journeys: ${e.message}")
                null
            }
        }
    }

    private suspend fun getTerminals(): List<Terminal>? {
        return withContext(Dispatchers.IO) {
            try {
                val terminalsResponse = terminalService.getTerminals()
                terminalsResponse.data
            } catch (e: Exception) {
                Log.e(ContentValues.TAG, "Error fetching journeys: ${e.message}")
                null
            }
        }
    }
}