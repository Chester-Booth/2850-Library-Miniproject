package com.library.server

import io.ktor.server.application.Application
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        get("/search") { call.searchPage() }
        post("/search") { call.searchResults() }
    }
}
