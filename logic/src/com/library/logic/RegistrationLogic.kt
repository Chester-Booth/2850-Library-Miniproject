package com.library.logic

import com.library.db.MAX_VARCHAR_LENGTH
import com.library.db.UsersTable
import com.password4j.Password
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.suspendTransaction

const val MIN_USERNAME_LENGTH = 4
const val MIN_EMAIL_LENGTH = 6
const val MAX_LENGTH = MAX_VARCHAR_LENGTH
const val MIN_PASSWORD_LENGTH = 8

// validate credentials
fun NewUserCredentials.emailIsValid() =
    when {
        // it is hard to consider all edge cases,
        // but it must at least contain one '.' and one '@'
        email.length < MIN_EMAIL_LENGTH -> false

        // a@b.cd
        email.length > MAX_LENGTH -> false

        email.all { it.isLetterOrDigit() || it == '.' || it == '@' } -> true

        else -> false
    }

fun NewUserCredentials.userIsValid() =
    when {
        username.length < MIN_USERNAME_LENGTH -> false
        username.length > MAX_LENGTH -> false
        username.all { it.isLetterOrDigit() || it == '_' } -> true
        else -> false
    }

fun NewUserCredentials.passwordIsValid() =
    when {
        password.length < MIN_PASSWORD_LENGTH -> false
        password.any { it.isWhitespace() } -> false
        else -> true
    }

fun NewUserCredentials.addressIsValid() =
    when {
        password.length > MAX_LENGTH -> false
        else -> true
    }

// add user to database
suspend fun addUser(credentials: NewUserCredentials) {
    suspendTransaction<Unit> {
        val currUsernames = UsersTable.selectAll().map { it[UsersTable.username] }
        val currEmails = UsersTable.selectAll().map { it[UsersTable.email] }

        require(credentials.username !in currUsernames) { "Username already exists" }
        require(credentials.email !in currEmails) { "Username already exists" }
        require(credentials.emailIsValid()) { "Invalid email" }
        require(credentials.userIsValid()) { "Invalid username" }
        require(credentials.passwordIsValid()) { "Invalid password" }
        require(credentials.passwordIsValid()) { "Invalid address" }

        val hash =
            Password
                .hash(credentials.password)
                .addRandomSalt(8)
                .withScrypt()
                .result

        UsersTable.insert {
            it[username] = credentials.username
            it[email] = credentials.email
            it[passwordHash] = hash
            it[address] = credentials.address
        }
    }
}
