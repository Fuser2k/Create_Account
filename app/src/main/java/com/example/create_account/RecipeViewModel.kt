package com.example.create_account

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RecipeViewModel : ViewModel() {

    // Tüm tarifler
    private val allRecipes = listOf(
        Recipe(1, "Pasta", R.drawable.pasta_image, "Delicious pasta with creamy sauce."),
        Recipe(2, "Burger", R.drawable.burger_image, "Juicy beef burger with cheese."),
        Recipe(3, "Pizza", R.drawable.pizza_image, "Classic Margherita pizza.")
    )

    // Filtrelenmiş tarifler için StateFlow
    private val _recipesFlow = MutableStateFlow(allRecipes)
    val recipesFlow: StateFlow<List<Recipe>> = _recipesFlow

    // Arama sorgusunu işleyen fonksiyon
    fun searchRecipes(query: String) {
        if (query.length < 3) {
            // Sorgu kısa ise tüm tarifleri göster
            _recipesFlow.update { allRecipes }
        } else {
            // Sorguya uygun tarifleri filtrele
            val filteredRecipes = allRecipes.filter { recipe ->
                recipe.name.contains(query, ignoreCase = true) ||
                        recipe.description.contains(query, ignoreCase = true)
            }
            _recipesFlow.update { filteredRecipes }
        }
    }
}
