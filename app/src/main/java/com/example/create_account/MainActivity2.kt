package com.example.create_account

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activitiy_main2)

        // Already a Member? Log In tıklama olayını ayarla
        val alreadyMemberText = findViewById<TextView>(R.id.alreadyMemberText)
        alreadyMemberText.setOnClickListener {
            // MainActivity'ye geçiş yap
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
