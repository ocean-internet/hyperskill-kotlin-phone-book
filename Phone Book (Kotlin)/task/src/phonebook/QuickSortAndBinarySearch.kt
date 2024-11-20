package phonebook

class QuickSortAndBinarySearch(phoneDirectory: PhoneDirectory, names: NameList) :
    SortAndSearch<SortedPhoneDirectory>(phoneDirectory, names, "quick sort + binary search") {

    override suspend fun sort(): SortedPhoneDirectory = phoneDirectory.quickSort()

    override fun search(phoneDirectory: SortedPhoneDirectory, name: Name): Entry? = phoneDirectory.binarySearch(name)
}
