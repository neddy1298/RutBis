package live.neddyap.rutbis.data.bus

import retrofit2.http.GET
import retrofit2.http.Path

interface BusInterface {

    @GET("buses")
    suspend fun getBuses(): BusesResponse

    @GET("bus/{bus_id}")
    suspend fun getBus(@Path("bus_id") id: String): BusResponse

}