package com.library.server

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication

class AccountTest :
    StringSpec({
        "Account Page Loads For Valid User" {
            testApplication {
                application {
                    testModule()
                }
                val response = client.get("/account/1")
                response.status shouldBe HttpStatusCode.OK
                response.bodyAsText().let {
                    it shouldContain "username"
                    it shouldContain "email@email.com"
                }
            }
        }
        "Account Page Shows Error For Invalid User" {
            testApplication {
                application {
                    testModule()
                }
                val response = client.get("/account/999")
                response.bodyAsText() shouldContain "User not found"
            }
        }
    })
