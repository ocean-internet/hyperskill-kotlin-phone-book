package phonebook

class HashTableAndSearch(phoneDirectory: PhoneDirectory, names: NameList) :
    SortAndSearch<PhoneDirectoryHashTable>(phoneDirectory, names, "hash table") {

    override suspend fun sort(): PhoneDirectoryHashTable = phoneDirectory.createHashTable()

    override fun search(phoneDirectory: PhoneDirectoryHashTable, name: Name): Entry? = phoneDirectory.get(name)

    override fun doReport(data: ReportData) {
        printReport(data.copy(sortType = "Creating"))
    }
}
