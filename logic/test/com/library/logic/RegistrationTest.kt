package com.library.logic

import io.kotest.assertions.withClue
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class RegistrationTest : StringSpec({

    // Email
    
    "an Email is valid when it contains at least one '.', one '@' and 6 characters" {
        withClue("guess=a@b.co") {
            val newUser = NewUserCredentials("user", "a@b.co", "hash")
            newUser.emailIsValid() shouldBe true 
        }
    }

    "an Email is not valid when it does not contain at least one '.', one '@' and 6 characters" {
        withClue("guess=a@bco") {
            val newUser = NewUserCredentials("user", "a@bco", "hash")
            newUser.emailIsValid() shouldBe false 
        }

        withClue("guess=ab.co") {
            val newUser = NewUserCredentials("user", "ab.co", "hash")
            newUser.emailIsValid() shouldBe false 
        }

        withClue("guess=abco") {
            val newUser = NewUserCredentials("user", "abco", "hash")
            newUser.emailIsValid() shouldBe false 
        }
    }

    // Username

    "a Username is valid when it contains digits, alpha characters and underscore" {
        withClue("guess=new_user") {
            val newUser = NewUserCredentials("new_user", "a@b.co", "hash")
            newUser.userIsValid() shouldBe true 
        }

        withClue("guess=new_user123") {
            val newUser = NewUserCredentials("new_user123", "a@b.co", "hash")
            newUser.userIsValid() shouldBe true 
        }
    }

    "a Username is not valid when it does not contain digits, alpha characters and underscore" {
        withClue("guess=   ") {
            val newUser = NewUserCredentials("   ", "a@b.co", "hash")
            newUser.userIsValid() shouldBe false 
        }

        withClue("guess=!!user!!") {
            val newUser = NewUserCredentials("!!user!!", "a@b.co", "hash")
            newUser.userIsValid() shouldBe false 
        }
    }

    // Password

    "a Password is valid when it contains at least the min amount of characters and no spaces" {
        withClue("guess=Xwmwdwo!2") {
            val newUser = NewUserCredentials("user", "a@b.co", "Xwmwdwo!2")
            newUser.passwordIsValid() shouldBe true 
        }
    }

    "a Password is not valid when it contains spaces" {
        withClue("guess= a  skke a") {
            val newUser = NewUserCredentials("useer", "a@b.co", " a  skke a")
            newUser.passwordIsValid() shouldBe false 
        }
    }


})