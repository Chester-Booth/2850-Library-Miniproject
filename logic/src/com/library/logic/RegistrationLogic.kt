package com.library.logic

import com.library.db.LibraryDatabase
import com.library.db.Users
import com.library.db.UsersTable
import com.library.db.MAX_VARCHAR_LENGTH
import com.password4j.Password
import org.jetbrains.exposed.v1.jdbc.transactions.suspendTransaction
import org.jetbrains.exposed.v1.jdbc.*
// fix wildcard import later
import org.jetbrains.exposed.v1.jdbc.transactions.transaction


const val MIN_USERNAME_LENGTH = 4
const val MIN_EMAIL_LENGTH = 6
const val MAX_LENGTH = MAX_VARCHAR_LENGTH
const val MIN_PASSWORD_LENGTH = 8

// validate credentials
suspend fun NewUserCredentials.emailIsValid() = when {
    // it is hard to consider all edge cases, 
    // but it must at least contain one '.' and one '@'
    email.length < MIN_EMAIL_LENGTH -> false // a@b.cd
    email.length > MAX_LENGTH -> false
    email.all { it.isLetterOrDigit() || it == '.' || it == '@'} -> true
    else -> false
}

suspend fun NewUserCredentials.userIsValid() = when {
    username.length < MIN_USERNAME_LENGTH -> false
    username.length > MAX_LENGTH -> false
    username.all { it.isLetterOrDigit() || it == '_' } -> true
    else -> false
}

suspend fun NewUserCredentials.passwordIsValid() = when {
    password.length < MIN_PASSWORD_LENGTH -> false
    password.any { it.isWhitespace() } -> false
    else -> true
}

// add user to database
suspend fun addUser(credentials: NewUserCredentials) {
    println("debug: adding a user")
    suspendTransaction<Unit> {
        val curr_usernames = UsersTable.selectAll().map { it[UsersTable.username] }
        val curr_emails = UsersTable.selectAll().map { it[UsersTable.email] }

        require(credentials.username !in curr_usernames) { "Username already exists" }
        require(credentials.email !in curr_emails) { "Username already exists" }
        require(credentials.emailIsValid()) { "Invalid email" }
        require(credentials.userIsValid()) { "Invalid username" }
        require(credentials.passwordIsValid()) { "Invalid password" }

        val hash = Password.hash(credentials.password).addRandomSalt(8).withScrypt().getResult() 

        UsersTable.insert {
            it[username] = credentials.username
            it[email] = credentials.email
            it[passwordHash] = hash // passwordHash
        }
    }
}