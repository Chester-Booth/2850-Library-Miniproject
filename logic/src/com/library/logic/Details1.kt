package com.library.logic

import com.library.db.Books
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

data class BookDetails(
    val title: String,
    val author: String,
    val bookISBN: Long,
    val details: String?,
    val coverUrl: String?  
)

fun getBookByIsbn(isbn: Long): BookDetails? =
    transaction {
        val book = Books.all().firstOrNull { it.bookISBN == isbn }
            ?: return@transaction null

        BookDetails(
            title = book.title,
            author = book.author,
            bookISBN = book.bookISBN ?: isbn,
            details = book.details,
            coverUrl = book.coverUrl
        )
    }