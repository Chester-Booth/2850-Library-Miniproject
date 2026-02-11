package com.library.server

import com.library.logic.getBookCount
import com.library.logic.search
import io.ktor.server.application.ApplicationCall
import io.ktor.server.pebble.respondTemplate
import io.ktor.server.request.receiveParameters
import java.io.File
import kotlin.collections.mapOf
import kotlin.text.lowercase

suspend fun ApplicationCall.searchPage() {
    // get data
    val results = getBookCount()
    // show page
    respondTemplate("search.peb", results)
}

suspend fun ApplicationCall.searchResults() {
    // get the params
    val formParams = receiveParameters()
    val searchTerm = formParams["q"]?.lowercase() ?: ""
    // get search results
    val results = search(searchTerm)
    // show page
    val data = mapOf("searchTerm" to searchTerm, "results" to results)
    respondTemplate("results.peb", data)
}
