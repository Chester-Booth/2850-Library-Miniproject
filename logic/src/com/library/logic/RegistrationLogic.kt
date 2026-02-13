package com.library.logic

import com.library.db.MAX_VARCHAR_LENGTH
import com.library.db.UsersTable
import com.password4j.Password
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.suspendTransaction
import kotlin.text.Regex
import kotlin.text.lowercase
import java.io.FileReader

const val MIN_USERNAME_LENGTH = 4
const val MIN_EMAIL_LENGTH = 6
const val MAX_LENGTH = MAX_VARCHAR_LENGTH
const val MIN_PASSWORD_LENGTH = 8
const val COMMON_PASSWORD_LIST = "./data/10k-most-common.txt"


// validate credentials
suspend fun NewUserCredentials.emailIsValid() =
    when {
        // it is hard to consider all edge cases,
        // but it must at least contain one '.', one '@' and no consecutive '.'
        email.length < MIN_EMAIL_LENGTH -> false

        // a@b.cd
        email.length > MAX_LENGTH -> false

        // could use character limits in regex, but it would be at expense of readability
        else -> Regex("^(?!.*\\.\\.)[^ .][^ ]*@[^ ]+\\.[^ .]+$").containsMatchIn(email)
    }

suspend fun NewUserCredentials.userIsValid() =
    when {
        username.length < MIN_USERNAME_LENGTH -> false
        username.length > MAX_LENGTH -> false
        else -> Regex("^\\w+$").containsMatchIn(username)
    }

suspend fun NewUserCredentials.passwordIsValid() =
    when {
        // password must contain numbers and uppercase letters
        password.length < MIN_PASSWORD_LENGTH -> false
        else -> Regex("^(?=.*[0-9])(?=.*[A-Z])[^ ]*$").containsMatchIn(password)
    }

suspend fun NewUserCredentials.passwordIsNotCommon(filePath: String) : Boolean {
    var notCommon = true
    FileReader(filePath).forEachLine { 
        if (password == it) notCommon = false
    }
    return notCommon
}

suspend fun NewUserCredentials.addressIsValid() =
    when {
        address.length > MAX_LENGTH -> false
        else -> Regex("^(?=.*[a-zA-z])[0-9A-za-z,.'/ ]*$").containsMatchIn(address)
    }

// add user to database
suspend fun addUser(credentials: NewUserCredentials) {
    suspendTransaction<Unit> {
        val currUsernames = UsersTable.selectAll().map { it[UsersTable.username] }
        val currEmails = UsersTable.selectAll().map { it[UsersTable.email] }
        val newUsername = credentials.username.lowercase()
        val newEmail = credentials.email.lowercase()

        require(newUsername !in currUsernames) { "Username already exists" }
        require(newEmail !in currEmails) { "Email already in use" }
        require(credentials.emailIsValid()) { "Invalid email" }
        require(credentials.userIsValid()) { "Invalid username" }
        require(credentials.passwordIsNotCommon(COMMON_PASSWORD_LIST)) { "Password is too weak" }
        require(credentials.passwordIsValid()) { "Invalid password" }
        require(credentials.addressIsValid()) { "Invalid address" }

        val hash =
            Password
                .hash(credentials.password)
                .addRandomSalt(8)
                .withScrypt()
                .result

        UsersTable.insert {
            it[username] = newUsername
            it[email] = newEmail
            it[passwordHash] = hash
            it[address] = credentials.address
        }
    }
}
