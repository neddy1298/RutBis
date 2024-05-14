package live.neddyap.rutbis.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import live.neddyap.rutbis.R
import live.neddyap.rutbis.databinding.FragmentExploreBinding
import live.neddyap.rutbis.databinding.FragmentFavoritesBinding
import live.neddyap.rutbis.databinding.HeaderBinding
import live.neddyap.rutbis.ui.favorites.FavoritesViewModel

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val viewModel =
            ViewModelProvider(this).get(FavoritesViewModel::class.java)
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.headerTitle
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root

    }

}