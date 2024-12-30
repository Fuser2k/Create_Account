package com.example.create_account

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Register Now linkine tıklama dinleyicisi ekleniyor
        val registerLink = findViewById<TextView>(R.id.registerLink)
        registerLink.setOnClickListener {
            // MainActivity2'ye geçiş yapılıyor
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}
