package live.neddyap.rutbis.data.bus

import java.io.Serializable

data class Bus(
    val busId: String,
    val busLicense: String,
    val busName: String,
    val busIcon: String,
    val busImage: String,
    val createdAt: String,
    val updatedAt: String
): Serializable

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