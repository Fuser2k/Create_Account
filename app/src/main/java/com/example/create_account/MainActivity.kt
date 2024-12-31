package com.example.create_account

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val credentialsManager = CredentialsManager() // Using the CredentialsManager class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Define UI components
        val emailEditText = findViewById<EditText>(R.id.emailField)
        val passwordEditText = findViewById<EditText>(R.id.passwordField)
        val loginButton = findViewById<Button>(R.id.proceedButton)
        val registerLink = findViewById<TextView>(R.id.registerLink)

        // Set click listener for "Register Now" link
        registerLink.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        // Set click listener for the login button
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            when {
                email.isEmpty() -> {
                    Toast.makeText(this, "Email field cannot be empty!", Toast.LENGTH_SHORT).show()
                }
                password.isEmpty() -> {
                    Toast.makeText(this, "Password field cannot be empty!", Toast.LENGTH_SHORT).show()
                }
                !credentialsManager.isEmailValid(email) -> {
                    Toast.makeText(this, "Invalid email format!", Toast.LENGTH_SHORT).show()
                }
                !credentialsManager.isPasswordValid(password) -> {
                    Toast.makeText(this, "Password must be at least 8 characters long!", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
