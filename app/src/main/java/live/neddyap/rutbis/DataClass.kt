package live.neddyap.rutbis

data class BusDataClass(var busId: Int, var dataImage: Int, var dataTitle: String)

data class TerminalDataClass(var terminalId: Int, var dataImage: Int, var dataTitle: String)

data class HistoryDataClass(var dataHistoryDate: String, var dataHistoryStart: String, var dataHistoryEnd: String, var dataHistoryTimeStart: String, var dataHistoryTimeEnd: String)