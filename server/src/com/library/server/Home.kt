package com.library.server

import io.ktor.server.application.ApplicationCall
import io.ktor.server.pebble.respondTemplate
import kotlin.collections.mapOf

suspend fun ApplicationCall.homePage() {
    val isLoggedIn = request.queryParameters["login"] == "true"
    var id = request.queryParameters["id"]

    if (id == null) {
        id = "n/a"
    }

    val data: Map<String, Any> =
        mapOf(
            "isLoggedIn" to isLoggedIn as Any,
            "id" to id as Any,
        )
    respondTemplate("index.peb", model = data)
}
