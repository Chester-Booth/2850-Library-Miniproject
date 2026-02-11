package com.library.server

import io.ktor.server.application.Application
import io.ktor.server.application.install
import com.library.logic.checkCredentials
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.UserIdPrincipal
import io.ktor.server.auth.UserPasswordCredential
import io.ktor.server.auth.form
import io.ktor.server.auth.session
import io.ktor.server.pebble.respondTemplate
import io.ktor.server.response.respondRedirect



fun Application.configureAuthentication() {
    install(Authentication) {
        form("auth-form") {
            userParamName = "username"
            passwordParamName = "password"
            validate { credentials ->
                when (checkCredentials(credentials)) {
                    true -> UserIdPrincipal(credentials.name)
                    false -> null
                }
            }
            challenge {
                call.respondTemplate("login.peb", mapOf(
                    "error" to "Invalid username or password!"
                ))
            }
        }
    }
}