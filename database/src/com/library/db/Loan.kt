package com.library.db

import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable
import org.jetbrains.exposed.v1.core.ReferenceOption
import org.jetbrains.exposed.v1.datetime.*

object LoanTable : IntIdTable("loan") {
    val dateOut = datetime("date_out").defaultExpression(CurrentDateTime) // set to current date when added
    val dateIn = datetime("date_in").nullable()
    val status = varchar("status", MAX_VARCHAR_LENGTH)
    val returnDate = datetime("return_date")
    val userId = reference("user_id", UsersTable, ReferenceOption.CASCADE)
    val copyId = reference("copy_id", CopiesTable, ReferenceOption.CASCADE)
}

// Entity Definition
class Loan(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Loan>(LoanTable)

    var dateOut by LoanTable.dateOut
    var dateIn by LoanTable.dateIn
    var status by LoanTable.status
    var returnDate by LoanTable.returnDate
    val userId by Users referencedOn LoanTable.userId // check
    val copyId by Copies referencedOn LoanTable.copyId // check

    override fun toString(): String {
        return "Users(id=$id, dateOut=$dateOut, dateIn=$dateOut, status=$status, userId=$userId)"
    }
}