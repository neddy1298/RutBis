package live.neddyap.rutbis.ui.explore

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import live.neddyap.rutbis.data.bus.Bus
import live.neddyap.rutbis.data.bus.BusInterface
import live.neddyap.rutbis.data.terminal.Terminal
import live.neddyap.rutbis.data.terminal.TerminalInterface
import live.neddyap.rutbis.databinding.FragmentExploreBinding
import live.neddyap.rutbis.databinding.ItemHeaderBinding
import live.neddyap.rutbis.network.ApiModule
import live.neddyap.rutbis.ui.FragmentPageAdapter
import live.neddyap.rutbis.ui.explore.bus.BusFragment
import live.neddyap.rutbis.ui.explore.terminal.TerminalFragment
import live.neddyap.rutbis.ui.search.SearchActivity

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null
    private lateinit var headerBinding: ItemHeaderBinding
    private val binding get() = _binding!!

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: FragmentPageAdapter

    private val busService: BusInterface by lazy {
        ApiModule.provideRetrofit().create(BusInterface::class.java)
    }

    private val terminalService: TerminalInterface by lazy {
        ApiModule.provideRetrofit().create(TerminalInterface::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        val viewModel = ViewModelProvider(this)[ExploreViewModel::class.java]
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        headerBinding = ItemHeaderBinding.bind(binding.root)
        val textView: TextView = headerBinding.headerTitle
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        val searchView = headerBinding.searchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        tabLayout = binding.exploreTabLayout
        viewPager2 = binding.exploreViewPager2
        adapter = FragmentPageAdapter(requireActivity().supportFragmentManager, lifecycle)

        binding.loadingProgressBar.visibility = View.VISIBLE

        viewLifecycleOwner.lifecycleScope.launch {
            val buses = getBuses()
            val terminals = getTerminals()

            withContext(Dispatchers.Main) {
                val busFragment = BusFragment().apply {
                    arguments = Bundle().apply {
                        putParcelableArrayList("buses", buses as ArrayList<out Parcelable>?)
                    }
                }
                adapter.addFragment(busFragment, "Bus")
                tabLayout.addTab(tabLayout.newTab().setText("Bus"))
                tabLayout.getTabAt(0)?.contentDescription = "Bus"

                val terminalFragment = TerminalFragment().apply {
                    arguments = Bundle().apply {
                        putParcelableArrayList("terminals", terminals as ArrayList<out Parcelable>?)
                    }
                }
                adapter.addFragment(terminalFragment, "Terminal")
                tabLayout.addTab(tabLayout.newTab().setText("Terminal"))
                tabLayout.getTabAt(1)?.contentDescription = "Terminal"

                viewPager2.adapter = adapter

                binding.loadingProgressBar.visibility = View.GONE
            }
        }


        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager2.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) { // Do nothing
            }

            override fun onTabReselected(tab: TabLayout.Tab?) { // Do nothing
            }
        })

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
        return root
    }

    private fun search(query: String?) {
        val intent = Intent(activity, SearchActivity::class.java).apply {
            putExtra("query", query)
        }
        startActivity(intent)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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