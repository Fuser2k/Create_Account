package com.example.create_account

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        // Yemek listesi ve resimler
        val recipes = listOf(
            Recipe(1, "Pasta", R.drawable.pasta_image, "Delicious pasta with creamy sauce."),
            Recipe(2, "Burger", R.drawable.burger_image, "Juicy beef burger with cheese."),
            Recipe(3, "Pizza", R.drawable.pizza_image, "Classic Margherita pizza.")
        )

        // RecyclerView'i bul ve ayarla
        val recyclerView = findViewById<RecyclerView>(R.id.recipeRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecipeAdapter(
            recipes,
            onItemClick = { recipe ->
                Toast.makeText(this, "Clicked: ${recipe.name}", Toast.LENGTH_SHORT).show()
            },
            onButtonClick = { recipe ->
                Toast.makeText(this, "Liked: ${recipe.name}", Toast.LENGTH_SHORT).show()
            }
        )

        // Çıkış butonu ayarı
        val logoutButton = findViewById<Button>(R.id.logoutButton)
        logoutButton.setOnClickListener {
            // MainActivity'ye yönlendir
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Bu activity'yi kapat
        }
    }
}
