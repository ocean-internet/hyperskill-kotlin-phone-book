package phonebook

class NoSortAndLinearSearch(phoneDirectory: PhoneDirectory, names: NameList) :
    SortAndSearch<PhoneDirectory>(phoneDirectory, names, "linear search") {

    override suspend fun sort(): PhoneDirectory = phoneDirectory

    override fun search(phoneDirectory: PhoneDirectory, name: Name): Entry? = phoneDirectory.linearSearch(name)

    override fun doReport(data: ReportData) {
        printReport(data.copy(isExtended = false))
    }
}
