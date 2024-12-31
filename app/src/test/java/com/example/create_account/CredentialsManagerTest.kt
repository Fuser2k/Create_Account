package com.example.create_account

import org.junit.Assert.*
import org.junit.Test

class CredentialsManagerTest {

    private val credentialsManager = CredentialsManager() // Instance of the class being tested

    @Test
    fun `test empty email returns false`() {
        val result = credentialsManager.emailValid("")
        assertFalse("Empty email should be invalid", result)
    }

    @Test
    fun `test invalid email format returns false`() {
        val result = credentialsManager.emailValid("example@com")
        assertFalse("Invalid email format should return false", result)
    }

    @Test
    fun `test valid email returns true`() {
        val result = credentialsManager.emailValid("example@example.com")
        assertTrue("Valid email format should return true", result)
    }

    @Test
    fun `test empty password returns false`() {
        val result = credentialsManager.validPassword("")
        assertFalse("Empty password should be invalid", result)
    }

    @Test
    fun `test valid password returns true`() {
        val result = credentialsManager.validPassword("securePassword123")
        assertTrue("Valid password should return true", result)
    }

    @Test
    fun `test short password returns false`() {
        val result = credentialsManager.validPassword("12345")
        assertFalse("Password shorter than 8 characters should be invalid", result)
    }

    @Test
    fun `test very long email returns false`() {
        val longEmail = "a".repeat(300) + "@example.com"
        val result = credentialsManager.emailValid(longEmail)
        assertFalse("Excessively long email should be invalid", result)
    }
}
