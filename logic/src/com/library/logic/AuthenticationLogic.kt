package com.library.logic

import com.library.db.Users
import com.library.db.UsersTable
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.transactions.suspendTransaction
import com.password4j.Password
import io.ktor.server.auth.UserPasswordCredential


suspend fun getUserFromDB(credentials: UserPasswordCredential) : String? = suspendTransaction {
    Users
        .find { UsersTable.username eq credentials.name }
        .map { it.username }
        .firstOrNull()
}

suspend fun getHashFromDB(credentials: UserPasswordCredential) : String? = suspendTransaction {
    Users
        .find { UsersTable.username eq credentials.name }
        .map { it.passwordHash }
        .firstOrNull()
}


suspend fun checkCredentials(credentials: UserPasswordCredential) : Boolean {
    val userFromDB = getUserFromDB(credentials)
    if (userFromDB == null) return false
    val hashFromDB = getHashFromDB(credentials)
    return Password.check(credentials.password, hashFromDB).withScrypt()
}


