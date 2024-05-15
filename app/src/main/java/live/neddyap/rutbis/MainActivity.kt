package live.neddyap.rutbis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import live.neddyap.rutbis.databinding.ActivityMainBinding
import live.neddyap.rutbis.ui.explore.ExploreFragment
import live.neddyap.rutbis.ui.favorites.FavoritesFragment
import live.neddyap.rutbis.ui.home.HomeFragment
import live.neddyap.rutbis.ui.promo.PromoFragment
import live.neddyap.rutbis.ui.settings.SettingsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    lateinit var binding: ActivityMainBinding


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
                    replaceFragment(PromoFragment())
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

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }
}