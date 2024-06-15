package live.neddyap.rutbis.ui.search

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import live.neddyap.rutbis.R
import live.neddyap.rutbis.databinding.FragmentSearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = binding.seachResult
        val query = intent.getStringExtra("query")

        "${resources.getString(R.string.search)} $query".also { result.text = it }

        if (query != null) {
            // Perform the search with the query
            Log.i(TAG, "onCreate: searching for $query")
        }

        val searchView = binding.searchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    fun back(view: View) {
        finish()
    }
}