package live.neddyap.rutbis.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import live.neddyap.rutbis.databinding.FragmentHomeBinding
import live.neddyap.rutbis.databinding.ItemHeaderBinding
import live.neddyap.rutbis.ui.search.SearchActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var headerBinding: ItemHeaderBinding

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val viewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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

        return root

    }

    private fun search(query: String?) {
        // Create an Intent to start SearchActivity
        val intent = Intent(activity, SearchActivity::class.java).apply {
            // Pass the query as an extra
            putExtra("query", query)
        }

        // Start SearchActivity
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}