package com.library.logic

import com.library.db.Users
import org.jetbrains.exposed.v1.jdbc.transactions.suspendTransaction

suspend fun getUserById(id: Int): Map<String, String>? {
    return suspendTransaction {
        val user = Users.findById(id) ?: return@suspendTransaction null
        mapOf(
            "username" to user.username,
            "email" to user.email,
            "address" to user.address,
            "passwordHash" to user.passwordHash,
        )
    }
}

suspend fun changePassword(
    id: Int,
    oldPassword: String,
    newPassword: String,
): String? {
    return suspendTransaction {
        val user = Users.findById(id) ?: return@suspendTransaction "User not found."

        if (user.passwordHash != oldPassword) {
            return@suspendTransaction "Incorrect current password."
        }
        if (newPassword.isBlank()) {
            return@suspendTransaction "New password cannot be empty."
        }

        user.passwordHash = newPassword // proper hashing later
        null
    }
}
