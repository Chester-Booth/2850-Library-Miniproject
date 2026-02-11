package com.library.server

import com.library.logic.getBookByIsbn
import io.ktor.server.application.ApplicationCall
import io.ktor.server.pebble.respondTemplate

suspend fun ApplicationCall.bookDetails() {
    val isbn = parameters["isbn"]?.toLongOrNull()
    if (isbn == null) {
        respondTemplate(
            "details.peb",
            mapOf("error" to "Invalid ISBN"),
        )
        return
    }

    val book = getBookByIsbn(isbn)
    if (book == null) {
        respondTemplate(
            "details.peb",
            mapOf("error" to "Book not found"),
        )
        return
    }

    respondTemplate(
        "details.peb",
        mapOf("book" to book),
    )
}
