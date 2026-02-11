package com.library.logic

import com.library.db.LibraryDatabase
import com.library.db.Users
import com.library.db.UsersTable
import com.library.db.MAX_VARCHAR_LENGTH

import org.jetbrains.exposed.v1.jdbc.transactions.suspendTransaction
import org.jetbrains.exposed.v1.jdbc.*
import org.jetbrains.exposed.v1.core.*

// fix wildcard import later
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
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


