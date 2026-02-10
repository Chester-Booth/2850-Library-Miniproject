package com.library.server

import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import kotlin.test.Test

class ApplicationTest {
    @Test
    fun testRoot() =
        testApplication {
            application {
                testModule()
            }
            val response = client.get("/search").also { checkForHtml(it) }
            response.bodyAsText().let {
                it shouldContain "<h1>Library - Search</h1>"
            }
        }
}

fun checkForHtml(response: HttpResponse) {
    response.status shouldBe HttpStatusCode.OK
    response.headers["Content-Type"]?.shouldContain("text/html")
}
