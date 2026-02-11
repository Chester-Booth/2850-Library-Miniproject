package com.library.server

import com.library.logic.getBookCount
import com.library.logic.search
import io.ktor.server.application.ApplicationCall
import io.ktor.server.pebble.respondTemplate
import io.ktor.server.request.receiveParameters
import kotlin.collections.mapOf
import kotlin.text.lowercase

suspend fun ApplicationCall.loginPage() {
    respondTemplate("login.peb", mapOf())
}

suspend fun ApplicationCall.loginUser() { }