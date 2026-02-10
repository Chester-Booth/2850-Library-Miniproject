package com.library.server

import io.ktor.server.application.Application
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        get("/") { call.search() }
        get("/search") { call.search() }
        post("/search") { call.searchResults() }
    }
}
