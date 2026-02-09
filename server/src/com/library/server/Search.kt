package com.library.server

import io.ktor.server.application.ApplicationCall
import io.ktor.server.pebble.respondTemplate
import io.ktor.server.request.receiveParameters
import kotlin.collections.mapOf
import kotlin.text.lowercase

suspend fun ApplicationCall.search() {
    // val results = logic.bookCount
    respondTemplate("search.peb", mapOf())
}

suspend fun ApplicationCall.searchResults() {
    val formParams = receiveParameters()
    val searchTerm = formParams["search_term"]?.lowercase() ?: ""
    // val results = logic.Search(searchTerm)
    // val data = mapOf("searchTerm" to searchTerm, "results" to results)
    // respondTemplate("results.peb", data)
}
