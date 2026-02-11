package com.library.server

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*

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
        "Account Page Successfully Changes Password" {
            testApplication {
                application {
                    testModule()
                }
                val response = client.post("/account/1/change-password") {
                    header("Content-Type", "application/x-www-form-urlencoded")
                    setBody("oldPassword=passwordHash&newPassword=newpass123")
                }
                response.status shouldBe HttpStatusCode.OK
                response.bodyAsText().let {
                    it shouldContain "Password updated successfully"
                }
            }
        }
    })