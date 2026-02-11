package com.library.db

import org.jetbrains.exposed.v1.jdbc.Database

object LibraryDatabase {
    const val URL = "jdbc:h2:./library"
    const val DRIVER = "org.h2.Driver"

    val db by lazy {
        Database.connect(URL, DRIVER)
    }

    
}
