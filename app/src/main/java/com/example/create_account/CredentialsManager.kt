package com.example.create_account

class CredentialsManager {

    // Validates if the email is in a correct format
    fun isEmailValid(email: String): Boolean {
        if (email.isEmpty()) return false // Empty email is invalid
        // Regex to match a valid email format
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        return Regex(emailRegex).matches(email) // Checks if email matches the pattern
    }

    // Validates if the password meets the criteria
    fun isPasswordValid(password: String): Boolean {
        // Password must not be empty and must have at least 8 characters
        return password.isNotEmpty() && password.length >= 8
    }

    // Checks for hardcoded credentials
    fun isHardcodedCredentials(email: String, password: String): Boolean {
        val hardcodedEmail = "test@te.st"
        val hardcodedPassword = "1234"
        return email == hardcodedEmail && password == hardcodedPassword
    }
}
