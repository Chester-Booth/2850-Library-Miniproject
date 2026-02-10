package com.library.server

import io.ktor.server.application.ApplicationCall
import io.ktor.server.pebble.respondTemplate
import kotlin.collections.mapOf

suspend fun ApplicationCall.homePage() {
    respondTemplate("index.peb", mapOf())
}
