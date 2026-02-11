package com.library.server

import com.library.db.TestDatabase
import io.ktor.server.application.Application
import org.jetbrains.exposed.v1.jdbc.transactions.TransactionManager
import kotlin.time.ExperimentalTime

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain
        .main(args)
}

@OptIn(ExperimentalTime::class)
fun Application.module() {
    TestDatabase.create()
    TransactionManager.defaultDatabase = TestDatabase.db
    // TransactionManager.defaultDatabase = LibraryDatabase.db
    configureTemplates()
    configureRouting()
}

@OptIn(ExperimentalTime::class)
fun Application.testModule() {
    TestDatabase.create()
    TransactionManager.defaultDatabase = TestDatabase.db
    configureTemplates()
    configureRouting()
}
