package com.library.logic

import com.library.db.Users
import com.library.db.UsersTable
import org.jetbrains.exposed.sql.transactions.transactions

fun getUserById(id: Int): Users? {
    return transaction {
        Users.findById(id)
    }
}

fun changePassword(id: Int, oldPassword: String, newPassword: String): String? {
    return transactions {
        val user = Users.findByID(id) ?: return@transaction "User not found."

        if (user.passwordHash != oldPassword) {
            return@transaction "Incorrect current password."
        }
        if (newPassword.isBlank()) {
            return@transaction "New password cannot be empty."
        }

        user.passwordHash = newPassword // proper hashing later
        null
    }
}