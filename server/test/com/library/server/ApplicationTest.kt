package com.library.server

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication

class ApplicationTest :
    StringSpec({

        "Home Page Loads and Shows H1" {
            testApplication {
                application {
                    testModule()
                }
                val response = client.get("/").also { checkForHtml(it) }
                response.bodyAsText().let {
                    it shouldContain "<h1>Library Application</h1>"
                }
            }
        }
    })

fun checkForHtml(response: HttpResponse) {
    response.status shouldBe HttpStatusCode.OK
    response.headers["Content-Type"]?.shouldContain("text/html")
}
