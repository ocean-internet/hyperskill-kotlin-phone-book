package phonebook

data class ReportData(
    val results: Int,
    val names: Int,
    val totalTime: Millis,
    val sortTime: Millis,
    val searchTime: Millis,
    val sortType: String = "Sorting",
    val hasStopped: Boolean = false,
    val isExtended: Boolean = true,
)