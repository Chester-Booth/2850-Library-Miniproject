package com.library.logic

// search implementation
// import com.library.db.*
import org.jetbrains.exposed.v1.core.like
import org.jetbrains.exposed.v1.core.lowerCase
import org.jetbrains.exposed.v1.jdbc.transactions.suspendTransaction

suspend fun search(term: String): List<Map<String, Any>> {
    val filtered =
        if (term.isBlank()) {
            allBooks
        } else {
            allBooks.filter {
                it.title.contains(term, ignoreCase = true)
            }
        }

    return filtered.map { book ->
        mapOf(
            "ISBN" to book.bookISBN,
            "title" to book.title,
            "author" to book.author,
        )
    }
/*
    suspendTransaction {
        db.find { Books.title.lowerCase() like "%$term%" }
    }*/
}

suspend fun getBookCount(): Map<String, Int> {
    /*
    suspendTransaction {
        val data = mapOf( "books" to Books.Count())
    }
    return data*/
    return mapOf("books" to allBooks.size)
}

data class Book(
    val bookISBN: Int,
    val title: String,
    val author: String,
)

private val allBooks =
    listOf(
        Book(1, "Kotlin in Action", author = "Kotlin in Action"),
        Book(2, "Programming in Kotlin", author = "Programming in Kotlin"),
        Book(3, "Web Development with Ktor", author = "Web Development with Ktor"),
    )
