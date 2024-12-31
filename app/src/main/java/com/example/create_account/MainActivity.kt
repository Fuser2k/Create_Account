package com.example.create_account

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private val credentialsManager = CredentialsManager() // Using the CredentialsManager class

    // Using lazy initialization for UI components
    private val emailFieldLayout: TextInputLayout by lazy { findViewById(R.id.emailFieldLayout) }
    private val passwordFieldLayout: TextInputLayout by lazy { findViewById(R.id.passwordFieldLayout) }
    private val proceedButton by lazy { findViewById<com.google.android.material.button.MaterialButton>(R.id.proceedButton) }
    private val registerLink by lazy { findViewById<android.widget.TextView>(R.id.registerLink) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // "Register Now" link click listener
        registerLink.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        // "Next" button click listener
        proceedButton.setOnClickListener {
            val email = emailFieldLayout.editText?.text.toString().trim()
            val password = passwordFieldLayout.editText?.text.toString().trim()

            var isValid = true

            // Validate email
            if (email.isEmpty()) {
                emailFieldLayout.error = "Email field cannot be empty!"
                isValid = false
            } else if (!credentialsManager.isEmailValid(email)) {
                emailFieldLayout.error = "Invalid email format!"
                isValid = false
            } else {
                emailFieldLayout.error = null // Clear previous error
            }

            // Validate password
            if (password.isEmpty()) {
                passwordFieldLayout.error = "Password field cannot be empty!"
                isValid = false
            } else if (!credentialsManager.isPasswordValid(password) && !credentialsManager.isHardcodedCredentials(email, password)) {
                passwordFieldLayout.error = "Password must be at least 8 characters long!"
                isValid = false
            } else {
                passwordFieldLayout.error = null // Clear previous error
            }

            // Check credentials
            if (isValid) {
                if (credentialsManager.isHardcodedCredentials(email, password)) {
                    Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity2::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Invalid credentials!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
