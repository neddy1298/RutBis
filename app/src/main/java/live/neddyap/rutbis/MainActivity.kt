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
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import live.neddyap.rutbis.data.bus.Bus
import live.neddyap.rutbis.data.bus.BusInterface
import live.neddyap.rutbis.databinding.ActivityMainBinding
import live.neddyap.rutbis.ui.explore.ExploreFragment
import live.neddyap.rutbis.ui.favorites.FavoritesFragment
import live.neddyap.rutbis.ui.home.HomeFragment
import live.neddyap.rutbis.ui.settings.SettingsFragment
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var bottomNavigationView: BottomNavigationView

    lateinit var binding: ActivityMainBinding

    private var mLocationPermissionGranted = false
    private lateinit var mMap: GoogleMap


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
                        replaceFragment(ExploreFragment(), busesResponse)
                    }
                }

                R.id.bottom_settings -> {
                    replaceFragment(SettingsFragment())
                }
            }
            true
        }
        replaceFragment(HomeFragment())
    }

    private fun replaceFragment(fragment: androidx.fragment.app.Fragment, data: List<Bus>? = null) {
        data?.let {
            val bundle = Bundle()
            bundle.putSerializable("buses", ArrayList(it))
            fragment.arguments = bundle
        }
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun getBuses(): List<Bus> {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BusInterface::class.java)

        return withContext(IO) {
            try {
                val busesResponse = retrofit.getBuses()
                Log.d(ContentValues.TAG, "busData: ${busesResponse.status}")
                Log.d(ContentValues.TAG, "busData: ${busesResponse.code}")

                busesResponse.data
            } catch (e: Exception) {
                Log.e(ContentValues.TAG, "busData-e: ${e.message}")
                emptyList<Bus>()
            }
        }
    }

    fun getBus(id: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BusInterface::class.java)

        try {
            val response = retrofit.getBus(id)
            val bus = response.data
            Log.d(ContentValues.TAG, "busData: ${bus.busId}")
            Log.d(ContentValues.TAG, "busData: ${bus.busLicense}")
            Log.d(ContentValues.TAG, "busData: ${bus.busName}")
            Log.d(ContentValues.TAG, "busData: ${bus.busIcon}")
            Log.d(ContentValues.TAG, "busData: ${bus.busImage}")
            Log.d(ContentValues.TAG, "busData: ${bus.createdAt}")
            Log.d(ContentValues.TAG, "busData: ${bus.updatedAt}")
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "busData-e: ${e.message}")
        }
    }
}