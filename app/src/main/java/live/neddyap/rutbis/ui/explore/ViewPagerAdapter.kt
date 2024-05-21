package live.neddyap.rutbis.ui.explore
//
//import androidx.fragment.app.Fragment
//import androidx.viewpager2.adapter.FragmentStateAdapter
//
//class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
//
//    private val fragmentsCreator: Map<Int, () -> Fragment> = mapOf(
//        0 to { BusFragment() },
//        1 to { TerminalFragment() }
//    )
//
//    override fun getItemCount() = fragmentsCreator.size
//
//    override fun createFragment(position: Int): Fragment {
//        return fragmentsCreator[position]?.invoke() ?: throw IndexOutOfBoundsException()
//    }
//}