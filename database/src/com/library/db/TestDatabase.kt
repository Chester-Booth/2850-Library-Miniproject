package com.library.db

import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.insertAndGetId
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

object TestDatabase {
    const val URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;"
    const val DRIVER = "org.h2.Driver"

    val db by lazy {
        Database.connect(URL, DRIVER)
    }

    fun create() {
        transaction(db) {
            SchemaUtils.drop(BooksTable, CopiesTable, LoanTable, ReservationsTable, UsersTable)
            SchemaUtils.create(BooksTable, CopiesTable, LoanTable, ReservationsTable, UsersTable)

            val book =
                BooksTable.insertAndGetId {
                    it[title] = "A Book"
                    it[author] = "Author A"
                    it[bookISBN] = 1029109201L
                }

            val copy =
                CopiesTable.insertAndGetId {
                    it[format] = "First Album"
                    it[bookId] = book
                }

            val user =
                UsersTable.insertAndGetId {
                    it[username] = "username"
                    it[email] = "email@email.com"
                    it[address] = "XXX-XXX"
                    it[passwordHash] = "passwordHash"
                }

            // expandable
        }
    }
}
