// Domain model for user
package com.library.logic

data class NewUserCredentials(
    val username: String,
    val email: String,
    val password: String
)