package com.library.server

import com.library.logic.getBookCount
import com.library.logic.search
import io.ktor.server.application.ApplicationCall
import io.ktor.server.pebble.respondTemplate
import io.ktor.server.request.receiveParameters
import kotlin.collections.mapOf
import kotlin.text.lowercase

suspend fun ApplicationCall.search() {
    val results = getBookCount()
    respondTemplate("search.peb", results)
}

suspend fun ApplicationCall.searchResults() {
    val formParams = receiveParameters()
    val searchTerm = formParams["q"]?.lowercase() ?: ""
    val results = search(searchTerm)
    val data = mapOf("searchTerm" to searchTerm, "results" to results)
    respondTemplate("results.peb", data)
}
