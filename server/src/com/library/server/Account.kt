package com.library.server

import com.library.logic.changePassword
import com.library.logic.getUserById
import io.ktor.server.application.ApplicationCall
import io.ktor.server.pebble.respondTemplate
import io.ktor.server.request.receiveParameters
import io.ktor.server.response.respondText

suspend fun ApplicationCall.accountPage() {
    val userId = parameters["id"]?.toIntOrNull()
    if (userId == null) {
        respondText("Invalid user ID.")
        return
    }
    val user = getUserById(userId)
    if (user == null) {
        respondText("User not found.")
        return
    }

    respondTemplate("account.peb", user + mapOf("id" to userId.toString()))
}

suspend fun ApplicationCall.updatePassword() {
    val userId = parameters["id"]?.toIntOrNull()
    if (userId == null) {
        respondText("Invalid user ID.")
        return
    }

    val formParams = receiveParameters()
    val oldPassword = formParams["oldPassword"] ?: ""
    val newPassword = formParams["newPassword"] ?: ""
    val error = changePassword(userId, oldPassword, newPassword)

    if (error != null) {
        val user = getUserById(userId) ?: emptyMap()
        respondTemplate("account.peb", user + mapOf("id" to userId.toString(), "error" to error))
        return
    }
    val user = getUserById(userId) ?: emptyMap()
    respondTemplate("account.peb", user + mapOf("id" to userId.toString(), "success" to "Password updated successfully."))
}
