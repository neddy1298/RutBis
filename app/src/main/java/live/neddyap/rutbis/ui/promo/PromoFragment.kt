package live.neddyap.rutbis.ui.promo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import live.neddyap.rutbis.databinding.FragmentHomeBinding
import live.neddyap.rutbis.databinding.HeaderBinding

class PromoFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var headerBinding: HeaderBinding

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val viewModel =
            ViewModelProvider(this).get(PromoViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        headerBinding = HeaderBinding.bind(binding.root)
        val textView: TextView = headerBinding.headerTitle
        val searchView: TextInputLayout = headerBinding.searchText
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
            searchView.hint = "Cari promo disini..."
        }

        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}