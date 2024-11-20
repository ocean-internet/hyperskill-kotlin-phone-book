package phonebook

import kotlinx.coroutines.withTimeoutOrNull

abstract class SortAndSearch<T>(val phoneDirectory: PhoneDirectory, val names: NameList, val searchType: String) {
    abstract suspend fun sort(): T
    abstract fun search(phoneDirectory: T, name: Name): Entry?

    suspend fun doSortAndSearch(maxSortTime: Millis? = null): Millis {
        println("Start searching (${searchType})...")

        val startSortTime = System.currentTimeMillis()
        val sortedPhoneDirectory =
            if (maxSortTime !== null) withTimeoutOrNull(maxSortTime) { sort() } else sort()
        var endSortTime = System.currentTimeMillis()

        val startSearchTime = System.currentTimeMillis()
        val results = names.mapNotNull { name ->
            if (sortedPhoneDirectory !== null) search(sortedPhoneDirectory, name) else phoneDirectory.linearSearch(name)
        }
        val endSearchTime = System.currentTimeMillis()

        doReport(
            ReportData(
                results = results.size,
                names = names.size,
                totalTime = endSearchTime - startSortTime,
                searchTime = endSearchTime - startSearchTime,
                sortTime = endSortTime - startSortTime,
                hasStopped = sortedPhoneDirectory == null,
            )
        )

        return endSearchTime - startSortTime
    }

    open fun doReport(data: ReportData) {
        printReport(data)
    }
}