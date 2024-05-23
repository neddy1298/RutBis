package live.neddyap.rutbis.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import live.neddyap.rutbis.databinding.FragmentExploreBinding
import live.neddyap.rutbis.databinding.ItemHeaderBinding
import live.neddyap.rutbis.ui.FragmentPageAdapter
import live.neddyap.rutbis.ui.explore.bus.BusFragment
import live.neddyap.rutbis.ui.explore.terminal.TerminalFragment

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null
    private lateinit var headerBinding: ItemHeaderBinding
    private val binding get() = _binding!!

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: FragmentPageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val viewModel =
            ViewModelProvider(this)[ExploreViewModel::class.java]
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        headerBinding = ItemHeaderBinding.bind(binding.root)
        val textView: TextView = headerBinding.headerTitle
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        tabLayout = binding.exploreTabLayout
        viewPager2 = binding.exploreViewPager2

        adapter = FragmentPageAdapter(requireActivity().supportFragmentManager, lifecycle)

        adapter.addFragment(BusFragment(), "Bus")
        tabLayout.addTab(tabLayout.newTab().setText("Bus"))
        tabLayout.getTabAt(0)?.contentDescription = "Bus"

        adapter.addFragment(TerminalFragment(), "Terminal")
        tabLayout.addTab(tabLayout.newTab().setText("Terminal"))
        tabLayout.getTabAt(1)?.contentDescription = "Terminal"

        viewPager2.adapter = adapter

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}