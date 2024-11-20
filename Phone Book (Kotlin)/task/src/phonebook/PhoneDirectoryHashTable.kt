package phonebook

class PhoneDirectoryHashTable() {
    private val storage = ('A'..'Z').toList()
        .associate { it to mutableListOf<Entry>() }

    fun put(entry: Entry) = storage[hash(entry.name)]?.add(entry)

    fun get(name: Name): Entry? = storage[hash(name)]?.firstOrNull { entry -> entry.name == name }

    fun hash(name: Name): Char = name.first().uppercase()[0]
}