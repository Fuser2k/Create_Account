package com.example.create_account

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class MainActivity2 : AppCompatActivity() {

    private val credentialsManager = CredentialsManager() // Using CredentialsManager for account registration

    // Lazy initialization of UI components
    private val fullNameInputLayout: TextInputLayout by lazy { findViewById(R.id.fullNameInputLayout) }
    private val emailInputLayout: TextInputLayout by lazy { findViewById(R.id.emailInputLayout) }
    private val phoneNumberInputLayout: TextInputLayout by lazy { findViewById(R.id.phoneNumberInputLayout) }
    private val passwordInputLayout: TextInputLayout by lazy { findViewById(R.id.passwordInputLayout) }
    private val registerButton by lazy { findViewById<com.google.android.material.button.MaterialButton>(R.id.registerButton) }
    private val alreadyMemberText by lazy { findViewById<android.widget.TextView>(R.id.alreadyMemberText) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitiy_main2)

        alreadyMemberText.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Register button click listener
        registerButton.setOnClickListener {
            val fullName = fullNameInputLayout.editText?.text.toString().trim()
            val email = emailInputLayout.editText?.text.toString().trim()
            val phoneNumber = phoneNumberInputLayout.editText?.text.toString().trim()
            val password = passwordInputLayout.editText?.text.toString().trim()

            var isValid = true

            if (fullName.isEmpty()) {
                fullNameInputLayout.error = "Full Name cannot be empty!"
                isValid = false
            } else {
                fullNameInputLayout.error = null
            }

            if (email.isEmpty()) {
                emailInputLayout.error = "Email cannot be empty!"
                isValid = false
            } else if (!credentialsManager.isEmailValid(email)) {
                emailInputLayout.error = "Invalid email format!"
                isValid = false
            } else {
                emailInputLayout.error = null
            }

            if (phoneNumber.isEmpty()) {
                phoneNumberInputLayout.error = "Phone Number cannot be empty!"
                isValid = false
            } else {
                phoneNumberInputLayout.error = null
            }

            if (password.isEmpty()) {
                passwordInputLayout.error = "Password cannot be empty!"
                isValid = false
            } else if (!credentialsManager.isPasswordValid(password)) {
                passwordInputLayout.error = "Password must be at least 8 characters long!"
                isValid = false
            } else {
                passwordInputLayout.error = null
            }

            if (isValid) {
                val registerResult = credentialsManager.register(email, password)
                if (registerResult) {
                    Toast.makeText(this, "Registration successful! Please log in.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Email is already taken!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
