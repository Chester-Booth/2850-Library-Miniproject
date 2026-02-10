package com.library.logic

// search implementation
import com.library.db.Books
import com.library.db.BooksTable
import org.jetbrains.exposed.v1.core.like
import org.jetbrains.exposed.v1.core.lowerCase
import org.jetbrains.exposed.v1.jdbc.transactions.suspendTransaction

suspend fun search(term: String): List<Map<String, Any?>> =
    // get the results from the database
    suspendTransaction {
        Books
            .find { BooksTable.title.lowerCase() like "%$term%" }
            .map { book ->
                mapOf(
                    "bookISBN" to book.bookISBN,
                    "title" to book.title,
                    "author" to book.author,
                )
            }
    }

suspend fun getBookCount(): Map<String, Long> =
    // get the number of books and return it
    suspendTransaction {
        mapOf("books" to Books.count())
    }
