package com.library.server

import com.library.logic.changePassword
import com.library.logic.getUserById
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.pebble.*

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

    respondTemplate("account.peb", user + mapOf("id" to userId))
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
        respondTemplate("account.peb", user + mapOf("error" to error))
        return
    }
    val user = getUserById(userId) ?: emptyMap()
    respondTemplate("account.peb", user + mapOf("success" to "Password upated successfully."))
}