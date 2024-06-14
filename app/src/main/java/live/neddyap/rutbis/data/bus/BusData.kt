package live.neddyap.rutbis.data.bus

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bus(
    val busId: String,
    val busLicense: String,
    val busName: String,
    val busIcon: Int,
    val busImage: String,
    val createdAt: String,
    val updatedAt: String
): Parcelable

data class BusResponse(
    val code: Int,
    val status: String,
    val data: Bus
)

data class BusesResponse(
    val code: Int,
    val status: String,
    val data: List<Bus>
)