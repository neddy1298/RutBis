package live.neddyap.rutbis.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import live.neddyap.rutbis.ui.explore.bus.BusFragment
import live.neddyap.rutbis.ui.explore.terminal.TerminalFragment

class FragmentPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0){
            BusFragment()
        } else {
            TerminalFragment()
        }
    }
}