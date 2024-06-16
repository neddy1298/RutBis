package live.neddyap.rutbis.ui.explore.terminal

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import live.neddyap.rutbis.data.journey.JourneyInterface
import live.neddyap.rutbis.data.journey.JourneysResponse
import live.neddyap.rutbis.data.terminal.TerminalInterface
import live.neddyap.rutbis.data.terminal.TerminalResponse
import live.neddyap.rutbis.databinding.ActivityTerminalDetailsBinding
import live.neddyap.rutbis.network.ApiModule

class TerminalDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTerminalDetailsBinding

    private val journeyService: JourneyInterface by lazy {
        ApiModule.provideRetrofit().create(JourneyInterface::class.java)
    }

    private val terminalService: TerminalInterface by lazy {
        ApiModule.provideRetrofit().create(TerminalInterface::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTerminalDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.terminalBusRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.loadingProgressBarDetail.visibility = View.VISIBLE // Show loading


        val terminalId = intent.getStringExtra("TERMINAL_ID") ?: ""

        CoroutineScope(Dispatchers.Main).launch {
            val journeysResponse = getJourneysForTerminal(terminalId)
            val terminalDetails = terminalDetails(terminalId)

            if (journeysResponse != null && terminalDetails != null) {
                Log.i(TAG, "journeysResponse data: $journeysResponse")
                val adapter = TerminalBusAdapter(journeysResponse.data)

                binding.terminalName.text = terminalDetails.data.terminalName
                binding.terminalLocation.text = terminalDetails.data.terminalLocation

                binding.terminalBusRecyclerView.adapter = adapter
            } else {
                "Error fetching data. Please try again.".showError()
            }
            binding.loadingProgressBarDetail.visibility = View.GONE // Hide loading
        }
    }

    private suspend fun getJourneysForTerminal(terminalId: String): JourneysResponse? {
        return withContext(Dispatchers.IO) {
            try {
                journeyService.getJourneysForTerminal(terminalId)
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching journeys: ${e.message}")
                null
            }
        }
    }

    private suspend fun terminalDetails(terminalId: String): TerminalResponse? {
        return withContext(Dispatchers.IO) {
            try {
                terminalService.getTerminal(terminalId)
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching terminal details: ${e.message}")
                null
            }
        }
    }
    private fun String.showError() {
        Toast.makeText(this@TerminalDetailActivity, this, Toast.LENGTH_SHORT).show()
        Log.e(TAG, this)
    }

    fun back(view: View) {
        finish()
    }
}