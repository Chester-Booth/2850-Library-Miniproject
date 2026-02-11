package com.library.server

import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.log
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.UserPasswordCredential
import io.ktor.server.auth.principal
import io.ktor.server.pebble.respondTemplate
import io.ktor.server.response.respondRedirect


suspend fun ApplicationCall.loginPage() {
    val registered = request.queryParameters["registered"] ?: "" // for newly registered users
    respondTemplate("login.peb", mapOf("registered" to registered))
}

suspend fun ApplicationCall.loginUser() { 
    val username = principal<UserIdPrincipal>()?.name.toString()
    application.log.info("User $username logged in")
    respondRedirect("/")
}