package com.library.db

import org.apache.commons.csv.CSVFormat
import org.jetbrains.exposed.v1.core.StdOutSqlLogger
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.insertAndGetId
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.io.FileReader

const val BOOKLIST = "./data/library_booklist.csv"

typealias ISBNToIdMap = LinkedHashMap<Long, EntityID<Int>>

// Creates Database
fun main(args: Array<String>) {
    val logging = args.isNotEmpty() && args[0].lowercase() == "--sql"

    transaction(LibraryDatabase.db) {
        if (logging) {
            addLogger(StdOutSqlLogger)
        }

        SchemaUtils.drop(BooksTable, CopiesTable, LoanTable, ReservationsTable, UsersTable)
        SchemaUtils.create(BooksTable, CopiesTable, LoanTable, ReservationsTable, UsersTable)

        val books = addBooks(BOOKLIST)
        addCopies(BOOKLIST, books)
    }
}

fun addBooks(filePath: String): ISBNToIdMap {
    FileReader(filePath).use { reader ->
        // remove 'duplicates'
        val records =
            CSVFormat.DEFAULT
                .parse(reader)
                .drop(1)
                .distinctBy { it[2] }
        val books = ISBNToIdMap()
        for (record in records) {
            if (record[2].isEmpty()) {
                continue
            } // skip entries without and ISBN
            val isbn = record[2].toDouble().toLong()
            books[isbn] =
                BooksTable.insertAndGetId {
                    it[title] = record[0]
                    it[author] = record[1]
                    it[bookISBN] = isbn
                    it[details] = null
                }
        }
        return books
    }
}

fun addCopies(
    filePath: String,
    books: ISBNToIdMap,
) {
    FileReader(filePath).use { reader ->
        val records = CSVFormat.DEFAULT.parse(reader).drop(1)
        for (record in records) {
            if (record[2].isEmpty()) {
                continue
            }
            val isbn = record[2].toDouble().toLong()
            val id = books.getOrElse(isbn) { continue }
            CopiesTable.insert {
                it[format] = record[3]
                it[bookId] = id
            }
        }
    }
}
