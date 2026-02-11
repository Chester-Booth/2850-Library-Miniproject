package com.library.server

import io.ktor.server.application.Application
import io.ktor.server.auth.authenticate
import io.ktor.server.http.content.staticResources
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        staticResources("/static", "static")
        get("/") { call.homePage() }
        get("/search") { call.searchPage() }
        get("/details/{isbn}") { call.bookDetails() }
        post("/search") { call.searchResults() }
        get("/account/{id}") { call.accountPage() }
        post("/account/{id}/change-password") { call.updatePassword() }
        get("/login") { call.loginPage() }
        authenticate("auth-form") {
            post("/login") { call.loginUser() }
        }
        get("/register") { call.registerPage() }
        post("/register") { call.registerUser() }
    }
}
