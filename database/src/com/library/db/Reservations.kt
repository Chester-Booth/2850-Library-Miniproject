package com.library.db

import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable
import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass
import org.jetbrains.exposed.v1.datetime.*

object ReservationsTable : IntIdTable("reservations") {
    val dateOut = datetime("date_out")
    val bookISBN = long("isbn")
    val userId = reference("user_id", UsersTable, ReferenceOption.CASCADE)
}

// Entity Definition
class Reservations(
    id: EntityID<Int>,
) : IntEntity(id) {
    companion object : IntEntityClass<Reservations>(ReservationsTable)

    var dateOut by ReservationsTable.dateOut
    var bookISBN by ReservationsTable.bookISBN
    var userId by Users referencedOn ReservationsTable.userId

    override fun toString(): String = "Reservations(id=$id, dateOut=$dateOut, isbn=$bookISBN, userId=$userId)"
}
