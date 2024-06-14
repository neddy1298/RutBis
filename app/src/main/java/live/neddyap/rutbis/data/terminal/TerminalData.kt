package live.neddyap.rutbis.data.terminal

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Terminal(
    val terminalId: String,
    val terminalName: String,
    val terminalLocation: String,
    val terminalIcon: Int,
    val terminalImage: String,
    val serviceTime: String,
    val createdAt: String,
    val updatedAt: String?
): Parcelable

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