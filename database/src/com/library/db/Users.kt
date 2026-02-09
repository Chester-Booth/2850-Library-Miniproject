package com.library.db

import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable
import org.jetbrains.exposed.v1.core.ReferenceOption

object UsersTable : IntIdTable("users") {
    val username = varchar("username", MAX_VARCHAR_LENGTH)
    val email = varchar("email", MAX_VARCHAR_LENGTH)
    val address = varchar("address", MAX_VARCHAR_LENGTH)
    val passwordHash = varchar("password_hash", MAX_VARCHAR_LENGTH)
}

// Entity Definition
class Users(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Users>(UsersTable)

    var username by UsersTable.username
    var email by UsersTable.email
    var address by UsersTable.address
    var passwordHash by UsersTable.passwordHash
    val reservation by Reservations referrersOn ReservationsTable.userId // check
    val loan by Loan referrersOn LoanTable.userId

    override fun toString(): String {
        return "Users(id=$id, username=$username)"
    }
}