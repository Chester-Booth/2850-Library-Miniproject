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
    val data = mapOf(
        "username" to user.username, "email" to user.email, "address" to user.address
    )
    respondTemplate("account.peb", data)
}

suspend fun ApplicationCall.updatePassword() {
    val userId = parameters["id"]?.toIntOrNull()
    if (userId == null) {
        respondText("Invalid user ID.")
        return
    }

    val formParams = receiveParameters()
    val oldPassword = formParams["oldPassword"]
    val newPassword = formParams["newPassword"]
    val error = changePassword(userId, oldPassword, newPassword)

    if (error != null) {
        val user = getUserById(userId)
        val data = mapOf(
            "username" to (user?.username ?: ""), "email" to (user?.email ?: ""),
            "address" to (user?.address ?: ""), "error" to error
        )
        respondTemplate("account.peb", data)
        return
    }

    val user = getUserById(userId)
    val data = mapOf(
        "username" to (user?.username ?: ""), "email" to (user?.email ?: ""),
        "address" to (user?.address ?: ""), "success" to "Password updated sccessfully."
    )
    respondTemplate("account.peb", data)
}