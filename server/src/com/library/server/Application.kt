package com.library.server

import com.library.db.LibraryDatabase
import io.ktor.server.application.Application
import org.jetbrains.exposed.v1.jdbc.transactions.TransactionManager

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain
        .main(args)
}

fun Application.module() {
    TransactionManager.defaultDatabase = LibraryDatabase.db
    configureTemplates()
    configureRouting()
}
