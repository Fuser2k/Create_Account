package com.example.create_account

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView // Doğru sınıfı import ettik
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest

class RecipeActivity : AppCompatActivity() {

    private val viewModel: RecipeViewModel by viewModels()
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        // RecyclerView'i bul ve ayarla
        val recyclerView = findViewById<RecyclerView>(R.id.recipeRecyclerView)
        recipeAdapter = RecipeAdapter(
            emptyList(),
            onItemClick = { recipe ->
                Toast.makeText(this, "Clicked: ${recipe.name}", Toast.LENGTH_SHORT).show()
            },
            onButtonClick = { recipe ->
                Toast.makeText(this, "Liked: ${recipe.name}", Toast.LENGTH_SHORT).show()
            }
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recipeAdapter

        // SearchView'i ayarla
        val searchView = findViewById<SearchView>(R.id.recipeSearchView) // Doğru sınıfı kullandık
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.searchRecipes(newText.orEmpty())
                return true
            }
        })

        // ViewModel'den filtrelenmiş tarifleri gözlemle
        lifecycleScope.launchWhenStarted {
            viewModel.recipesFlow.collectLatest { recipes ->
                recipeAdapter.updateRecipes(recipes)
            }
        }

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
