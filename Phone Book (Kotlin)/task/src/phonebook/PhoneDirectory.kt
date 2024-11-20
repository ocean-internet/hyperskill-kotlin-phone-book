package phonebook

import kotlinx.coroutines.yield
import java.io.File
import kotlin.math.floor
import kotlin.math.sqrt

typealias PhoneDirectory = List<Entry>
typealias MutablePhoneDirectory = MutableList<Entry>
typealias SortedPhoneDirectory = PhoneDirectory

fun FileName.createPhoneDirectory(): PhoneDirectory {
    val splitRegex = Regex("(\\d+)\\s+(.+)")
    return File(this).useLines { it.toList() }.map {
        val groups = splitRegex.find(it)?.groups
        val phoneNumber = groups?.get(1)?.value ?: ""
        val name = groups?.get(2)?.value ?: ""
        Entry(phoneNumber, name)
    }
}

fun MutablePhoneDirectory.swapIndex(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}

suspend fun PhoneDirectory.bubbleSort(): SortedPhoneDirectory {
    val sortedPhoneDirectory = this.toMutableList()

    val n = sortedPhoneDirectory.size
    var swapped: Boolean

    for (i in 0 until n - 1) {
        yield() // allow function to timeout
        swapped = false
        for (j in 0 until n - i - 1) {
            yield() // allow function to timeout
            if (sortedPhoneDirectory[j].name > sortedPhoneDirectory[j + 1].name) {
                sortedPhoneDirectory.swapIndex(j, j + 1)
                swapped = true
            }
        }
        if (!swapped) break
    }

    return sortedPhoneDirectory.toList()
}

suspend fun PhoneDirectory.quickSort(): SortedPhoneDirectory {

    val sortedPhoneDirectory = this.toMutableList()

    suspend fun sort(left: Int, right: Int) {
        var start = left
        var end = right
        val pivot = sortedPhoneDirectory[(left + right) / 2]

        while (start <= end) {
            yield() //allow function to timeout
            while (sortedPhoneDirectory[start].name < pivot.name) {
                start++
            }
            while (sortedPhoneDirectory[end].name > pivot.name) {
                end--
            }
            if (start <= end) {
                sortedPhoneDirectory.swapIndex(start, end)
                start++
                end--
            }
        }

        if (left < end) {
            sort(left, end)
        }
        if (start < right) {
            sort(start, right)
        }
    }

    sort(0, sortedPhoneDirectory.size - 1)

    return sortedPhoneDirectory.toList()
}

suspend fun PhoneDirectory.createHashTable(): PhoneDirectoryHashTable {
    val hashTable = PhoneDirectoryHashTable()

    this.forEach {
        yield()
        hashTable.put(it)
    }

    return hashTable
}

fun PhoneDirectory.linearSearch(name: Name): Entry? {
    var entry: Entry? = null
    val iterator = this.iterator()
    while (iterator.hasNext()) {
        val next = iterator.next()
        if (next.name == name) {
            entry = next
            break
        }
    }
    return entry
}

fun SortedPhoneDirectory.jumpSearch(value: Name): Entry? {
    if (this.isEmpty()) return null

    var curr = 0
    var prev = curr

    val last = this.size - 1
    val step = floor(sqrt(this.size.toDouble())).toInt()

    while (this.get(curr).name < value) {
        if (curr == last) return null
        prev = curr
        curr = (curr + step).coerceAtMost(last)
    }

    if (this.get(curr).name == value) return this.get(curr)

    while (this.get(curr).name > value) {
        curr--
        if (curr <= prev) return null
        if (this.get(curr).name == value) return this.get(curr)
    }

    return null
}

fun SortedPhoneDirectory.binarySearch(value: Name): Entry? {
    var left = 0
    var right = this.size - 1
    while (left <= right) {
        val middle = (left + right) / 2
        if (this[middle].name == value) {
            return this[middle]
        }
        if (this[middle].name > value) {
            right = middle - 1
        } else {
            left = middle + 1
        }
    }
    return null
}
