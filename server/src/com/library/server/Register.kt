package com.library.server

import com.library.logic.addUser
import com.library.logic.NewUserCredentials
import io.ktor.server.application.ApplicationCall
import io.ktor.server.pebble.respondTemplate
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respondRedirect
import kotlin.collections.mapOf
import io.ktor.server.util.getOrFail
import io.ktor.server.application.log


suspend fun ApplicationCall.registerPage() {
    respondTemplate("register.peb", mapOf())
}

suspend fun ApplicationCall.registerUser() {
    val credentials = getCredentials()
    val result = runCatching {
        addUser(credentials)
    }

    if (result.isSuccess) {
        application.log.info("User ${credentials.username} registered")
        respondRedirect("/login?registered=true") // redirect to log in page
    } else {
        val error = result.exceptionOrNull()?.message ?: ""
        respondTemplate("register.peb", model = mapOf("error" to error))
    }
   
}

private suspend fun ApplicationCall.getCredentials(): NewUserCredentials {
    val formParams = receiveParameters()
    val username = formParams.getOrFail("username")
    val email = formParams.getOrFail("email")
    val password = formParams.getOrFail("password")
    val address = formParams.getOrFail("address")
    return NewUserCredentials(username, email, password, address)
}