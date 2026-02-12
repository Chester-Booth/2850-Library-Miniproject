package com.library.logic

import io.kotest.assertions.withClue
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

const val PASSWORD_TEST_FILE = "../data/test/passwords.txt"

class RegistrationTest :
    StringSpec({

        // Email

        "an Email is valid when it contains at least one '.', one '@' and 6 characters" {
            withClue("guess=a@b.co") {
                val newUser = NewUserCredentials("user", "a@b.co", "hash", "add")
                newUser.emailIsValid() shouldBe true
            }
        }

        "an Email is not valid when it does not contain at least one '.', one '@' and 6 characters" {
            withClue("guess=a@bco") {
                val newUser = NewUserCredentials("user", "a@bco", "hash", "add")
                newUser.emailIsValid() shouldBe false
            }

            withClue("guess=ab.co") {
                val newUser = NewUserCredentials("user", "ab.co", "hash", "add")
                newUser.emailIsValid() shouldBe false
            }

            withClue("guess=abco") {
                val newUser = NewUserCredentials("user", "abco", "hash", "add")
                newUser.emailIsValid() shouldBe false
            }

            withClue("guess=ab..@co") {
                val newUser = NewUserCredentials("user", "ab..@co", "hash", "add")
                newUser.emailIsValid() shouldBe false
            }
        }

        // Username

        "a Username is valid when it contains digits, alpha characters and underscore" {
            withClue("guess=new_user") {
                val newUser = NewUserCredentials("new_user", "a@b.co", "hash", "add")
                newUser.userIsValid() shouldBe true
            }

            withClue("guess=new_user123") {
                val newUser = NewUserCredentials("new_user123", "a@b.co", "hash", "add")
                newUser.userIsValid() shouldBe true
            }
        }

        "a Username is not valid when it does not contain digits, alpha characters and underscore" {
            withClue("guess=   ") {
                val newUser = NewUserCredentials("   ", "a@b.co", "hash", "add")
                newUser.userIsValid() shouldBe false
            }

            withClue("guess=!!user!!") {
                val newUser = NewUserCredentials("!!user!!", "a@b.co", "hash", "add")
                newUser.userIsValid() shouldBe false
            }
        }

        // Password

        "a Password is valid when it contains uppercase letters, numbers, no spaces and it is >8 chars" {
            withClue("guess=Xwmwdwo!2") {
                val newUser = NewUserCredentials("user", "a@b.co", "Xwmwdwo!2", "add")
                newUser.passwordIsValid() shouldBe true
            }
        }

        "a Password is not valid when it does not contains uppercase letters, numbers, no spaces and it is >8 chars" {
            withClue("guess= a  skke a") {
                val newUser = NewUserCredentials("user", "a@b.co", " a  skke a", "add")
                newUser.passwordIsValid() shouldBe false
            }

            withClue("guess=password") {
                val newUser = NewUserCredentials("user", "a@b.co", "password", "add")
                newUser.passwordIsValid() shouldBe false
            }

            withClue("guess=password1") {
                val newUser = NewUserCredentials("user", "a@b.co", "password1", "add")
                newUser.passwordIsValid() shouldBe false
            }

            withClue("guess=12345678") {
                val newUser = NewUserCredentials("user", "a@b.co", "12345678", "add")
                newUser.passwordIsValid() shouldBe false
            }

            withClue("guess=!!@!!@!@!") {
                val newUser = NewUserCredentials("user", "a@b.co", "!!@!!@!@!", "add")
                newUser.passwordIsValid() shouldBe false
            }

            withClue("guess=abc") {
                val newUser = NewUserCredentials("user", "a@b.co", "abc", "add")
                newUser.passwordIsValid() shouldBe false
            }
        }

        "a Password is weak when it is in the common passwords list" {
            val newUser = NewUserCredentials("user", "a@b.co", "dragon", "add")
            newUser.passwordIsNotCommon(PASSWORD_TEST_FILE) shouldBe false
        }

        "a Password is not weak when it is not in the common passwords list" {
            val newUser = NewUserCredentials("user", "a@b.co", "Xwmwdwo!2", "add")
            newUser.passwordIsNotCommon(PASSWORD_TEST_FILE) shouldBe true
        }

        // Address

        "an Address is valid if it contains numbers, alpha characters and symbols ,.'/ " {
            withClue("guess=1 Building, Street, XXX XXX") {
                val newUser = NewUserCredentials("useer", "a@b.co", "abc", "1 Building, Street, XXX XXX")
                newUser.addressIsValid() shouldBe true
            }
        }

        "an Address is not valid if it does not contain alpha characters" {
            withClue("guess=1111") {
                val newUser = NewUserCredentials("useer", "a@b.co", "abc", "1111")
                newUser.addressIsValid() shouldBe false
            }
        }
    })
