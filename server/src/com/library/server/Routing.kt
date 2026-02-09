package com.library.server

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") { call.search() }
        get("/search") { call.search() }
        post("/search") { call.searchResults() }
    }
}
