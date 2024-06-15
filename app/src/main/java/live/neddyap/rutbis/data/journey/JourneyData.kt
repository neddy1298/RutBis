package live.neddyap.rutbis.data.journey

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import live.neddyap.rutbis.data.bus.Bus
import live.neddyap.rutbis.data.terminal.Terminal

@Parcelize
data class Journey(
    val journeyId: String,
    val busId: Bus,
    val terminalId: Terminal,
    val price: Int,
    val departureTime: String,
    val arrivalTime: String,
    val createdAt: String,
    val updatedAt: String?
) : Parcelable

data class JourneysResponse(
    val code: Int,
    val status: String,
    val data: List<Journey>
)