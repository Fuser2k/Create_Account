package com.example.create_account

import org.junit.Assert.*
import org.junit.Test

class CredentialsManagerTest {

    private val credentialsManager = CredentialsManager() // Instance of the class being tested

    @Test
    fun `test empty email returns false`() {
        val result = credentialsManager.isEmailValid("")
        assertFalse("Empty email should be invalid", result)
    }

    @Test
    fun `test invalid email format returns false`() {
        val result = credentialsManager.isEmailValid("example@com")
        assertFalse("Invalid email format should return false", result)
    }

    @Test
    fun `test valid email returns true`() {
        val result = credentialsManager.isEmailValid("example@example.com")
        assertTrue("Valid email format should return true", result)
    }

    @Test
    fun `test empty password returns false`() {
        val result = credentialsManager.isPasswordValid("")
        assertFalse("Empty password should be invalid", result)
    }

    @Test
    fun `test short password returns false`() {
        val result = credentialsManager.isPasswordValid("12345")
        assertFalse("Password shorter than 8 characters should be invalid", result)
    }

    @Test
    fun `test valid password returns true`() {
        val result = credentialsManager.isPasswordValid("securePassword123")
        assertTrue("Valid password should return true", result)
    }

    @Test
    fun `test hardcoded credentials return true`() {
        val email = "test@te.st"
        val password = "1234"
        val result = credentialsManager.isHardcodedCredentials(email, password)
        assertTrue("Hardcoded credentials should return true", result)
    }

    @Test
    fun `test invalid hardcoded credentials return false`() {
        val email = "wrong@te.st"
        val password = "wrong"
        val result = credentialsManager.isHardcodedCredentials(email, password)
        assertFalse("Invalid hardcoded credentials should return false", result)
    }

    @Test
    fun `test very long email returns false`() {
        val longEmail = "a".repeat(300) + "@example.com"
        val result = credentialsManager.isEmailValid(longEmail)
        assertFalse("Excessively long email should be invalid", result)
    }
}
