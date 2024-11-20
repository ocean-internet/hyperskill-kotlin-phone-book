package phonebook

import kotlinx.coroutines.runBlocking

const val PHONE_DIRECTORY_FILENAME = "../../../data/directory.txt"
const val PHONE_DIRECTORY_FILENAME_DEBUG = "../data/small_directory.txt"

const val NAMES_FILENAME = "../../../data/find.txt"
const val NAMES_FILENAME_DEBUG = "../data/small_find.txt"

fun main(args: Array<String>): Unit = runBlocking {

    val isDebug = args.contains("--debug")
    val directoryFilename = if (isDebug) PHONE_DIRECTORY_FILENAME_DEBUG else PHONE_DIRECTORY_FILENAME
    val namesFilename = if (isDebug) NAMES_FILENAME_DEBUG else NAMES_FILENAME

    val phoneDirectory = directoryFilename.createPhoneDirectory()
    val names = namesFilename.createNameList()

    val linearSearchMillis = NoSortAndLinearSearch(phoneDirectory, names).doSortAndSearch()
    val maxSortTime: Millis = linearSearchMillis * 10

    BubbleSortAndJumpSearch(phoneDirectory, names).doSortAndSearch(maxSortTime)
    QuickSortAndBinarySearch(phoneDirectory, names).doSortAndSearch(maxSortTime)
    HashTableAndSearch(phoneDirectory, names).doSortAndSearch(maxSortTime)
}
