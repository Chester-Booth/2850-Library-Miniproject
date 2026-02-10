package com.library.db

import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable

const val MAX_VARCHAR_LENGTH = 256

// Table Definition
object BooksTable : IntIdTable("books") {
    val title = varchar("title", MAX_VARCHAR_LENGTH)
    val author = varchar("author", MAX_VARCHAR_LENGTH)
    val bookISBN = long("isbn").nullable() // integer is too short
    val details = varchar("details", MAX_VARCHAR_LENGTH).nullable()
}

// Entity Definition
class Books(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Books>(BooksTable)

    var title by BooksTable.title
    var author by BooksTable.author
    var bookISBN by BooksTable.bookISBN
    var details by BooksTable.details
    val copies by Copies referrersOn CopiesTable.bookId


    override fun toString(): String {
        return "Books(id=$id, title=$title, isbn=$bookISBN)"
    }
}