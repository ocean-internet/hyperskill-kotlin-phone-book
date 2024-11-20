package phonebook

import java.io.File

typealias NameList = List<Name>

fun FileName.createNameList(): NameList = File(this).useLines<NameList> { it.toList() }
