package phonebook

class BubbleSortAndJumpSearch(phoneDirectory: PhoneDirectory, names: NameList) :
    SortAndSearch<SortedPhoneDirectory>(phoneDirectory, names, "bubble sort + jump search") {

    override suspend fun sort(): SortedPhoneDirectory = phoneDirectory.bubbleSort()

    override fun search(phoneDirectory: SortedPhoneDirectory, name: Name): Entry? = phoneDirectory.jumpSearch(name)
}