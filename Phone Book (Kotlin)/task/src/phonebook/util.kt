package phonebook

typealias FileName = String

const val SECOND_IN_MILLIS = 1000
const val MINUTE_IN_MILLIS = SECOND_IN_MILLIS * 60

typealias Millis = Long

fun Millis.toTimeString() = listOf(
    "${this / MINUTE_IN_MILLIS} min.",
    "${this / SECOND_IN_MILLIS} sec.",
    "${this % SECOND_IN_MILLIS} ms.",
).joinToString(" ")

fun printReport(data: ReportData) {
    println("Found ${data.results} / ${data.names} entries.")
    println("Time taken: ${(data.totalTime).toTimeString()}\n")
    if (data.isExtended) {
        print("${data.sortType} time: ${(data.sortTime).toTimeString()}")
        if (data.hasStopped) print(" - STOPPED, moved to linear search")
        print("\n")
        println("Searching time: ${(data.searchTime).toTimeString()}\n")
    }
}