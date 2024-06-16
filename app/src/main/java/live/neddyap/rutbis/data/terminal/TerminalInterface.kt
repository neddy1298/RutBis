package live.neddyap.rutbis.data.terminal

import retrofit2.http.GET
import retrofit2.http.Path

interface TerminalInterface {

    @GET("terminals")
    suspend fun getTerminals(): TerminalsResponse

    @GET("terminal/{terminal_id}")
    suspend fun getTerminal(@Path("terminal_id") id: String): TerminalResponse

}