package live.neddyap.rutbis.ui.explore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import live.neddyap.rutbis.R
import live.neddyap.rutbis.databinding.FragmentExploreBinding
import live.neddyap.rutbis.databinding.HeaderBinding

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null
    private lateinit var headerBinding: HeaderBinding

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val viewModel =
            ViewModelProvider(this).get(ExploreViewModel::class.java)
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        headerBinding = HeaderBinding.bind(binding.root)
        val textView: TextView = headerBinding.headerTitle
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root

    }

}