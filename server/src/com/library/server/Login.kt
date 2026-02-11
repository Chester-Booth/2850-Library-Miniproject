package com.library.server

import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.log
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.UserPasswordCredential
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.principal
import io.ktor.server.pebble.respondTemplate
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respondRedirect
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import io.ktor.server.sessions.clear
import io.ktor.server.sessions.sessions
import io.ktor.server.sessions.set
import io.ktor.server.util.getOrFail

suspend fun ApplicationCall.loginPage() {
    respondTemplate("login.peb", mapOf())
}

suspend fun ApplicationCall.loginUser() { 
    val username = principal<UserIdPrincipal>()?.name.toString()
    println("debug: here")
    application.log.info("User $username logged in")
    respondRedirect("/")
    //sessions.set(UserSession(username, 1))
}