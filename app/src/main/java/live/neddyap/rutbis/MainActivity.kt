package live.neddyap.rutbis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import live.neddyap.rutbis.databinding.ActivityMainBinding
import live.neddyap.rutbis.ui.explore.ExploreFragment
import live.neddyap.rutbis.ui.favorites.FavoritesFragment
import live.neddyap.rutbis.ui.home.HomeFragment
import live.neddyap.rutbis.ui.settings.SettingsFragment

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var bottomNavigationView: BottomNavigationView

    lateinit var binding: ActivityMainBinding
    private var mLocationPermissionGranted = false
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_home -> {
//                    headerBinding.headerTitle.text = resources.getString(R.string.header_home)
                    replaceFragment(HomeFragment())
                }

                R.id.bottom_promo -> {
                    replaceFragment(live.neddyap.rutbis.ui.history.HistoryFragment())
                }

                R.id.bottom_favorite -> {
                    replaceFragment(FavoritesFragment())
                }

                R.id.bottom_explore -> {
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

    private fun replaceFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}