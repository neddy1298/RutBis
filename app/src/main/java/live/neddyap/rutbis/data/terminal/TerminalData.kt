package live.neddyap.rutbis.data.terminal

import java.io.Serializable

data class Terminal(
    val terminalId: String,
    val terminalName: String,
    val terminalLocation: String,
    val terminalImage: String,
    val serviceTime: String,
    val createdAt: String,
    val updatedAt: String?
): Serializable

data class TerminalResponse(
    val code: Int,
    val status: String,
    val data: Terminal
)

data class TerminalsResponse(
    val code: Int,
    val status: String,
    val data: List<Terminal>
)