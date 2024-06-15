package live.neddyap.rutbis.data.journey

import retrofit2.http.GET
import retrofit2.http.Path

interface JourneyInterface {
    @GET("journeys/bus/{bus_id}")
    suspend fun getJourneysForBus(@Path("bus_id") busId: String): JourneysResponse

    @GET("journeys/terminal/{terminal_id}")
    suspend fun getJourneysForTerminal(@Path("terminal_id") terminalId: String): JourneysResponse
}