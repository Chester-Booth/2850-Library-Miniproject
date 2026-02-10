package com.library.db

import org.jetbrains.exposed.v1.dao.IntEntity
import org.jetbrains.exposed.v1.dao.IntEntityClass
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.IntIdTable
import org.jetbrains.exposed.v1.core.ReferenceOption

// Table Definition
object CopiesTable : IntIdTable("copies") {
    val bookId = reference("book_id", BooksTable, ReferenceOption.CASCADE)
    val format = varchar("format", MAX_VARCHAR_LENGTH)
}

// Entity Definition
class Copies(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Copies>(CopiesTable)

    var bookId by Books referencedOn CopiesTable.bookId
    var format by CopiesTable.format
    //val loan by Loan referrersOn LoanTable.copyId // 1 copy, 1 loan

    override fun toString(): String {
        return "Copies(id=$id, bookId=$bookId, format=$format)"
    }
}