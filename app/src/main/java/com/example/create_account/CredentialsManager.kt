package com.example.create_account

class CredentialsManager {

    private val accounts = mutableMapOf<String, String>()

    fun isEmailValid(email: String): Boolean {
        if (email.isEmpty()) return false
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        return Regex(emailRegex).matches(email)
    }

    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty() && password.length >= 8
    }

    // Checks for hardcoded credentials
    fun isHardcodedCredentials(email: String, password: String): Boolean {
        val hardcodedEmail = "test@te.st"
        val hardcodedPassword = "1234"
        return email == hardcodedEmail && password == hardcodedPassword
    }

    fun register(email: String, password: String): Boolean {
        if (accounts.containsKey(email)) {
            return false
        }
        accounts[email] = password
        return true
    }

    fun validateCredentials(email: String, password: String): Boolean {
        return accounts[email] == password || isHardcodedCredentials(email, password)
    }
}
