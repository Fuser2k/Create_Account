package com.example.create_account

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(
    private var recipes: List<Recipe>, // Listeyi güncellenebilir yapmak için "var" olarak değiştirildi
    private val onItemClick: (Recipe) -> Unit,
    private val onButtonClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    // ViewHolder tanımı
    inner class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.recipeImageView)
        val name: TextView = view.findViewById(R.id.recipeNameTextView)
        val likeButton: Button = view.findViewById(R.id.likeButton)
    }

    // ViewHolder oluşturma
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    // ViewHolder'ı bağlama
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.image.setImageResource(recipe.imageResId) // Resmi ayarla
        holder.name.text = recipe.name // İsmi ayarla
        holder.itemView.setOnClickListener { onItemClick(recipe) } // Tıklama olayı
        holder.likeButton.setOnClickListener { onButtonClick(recipe) } // Buton tıklama olayı
    }

    // Listedeki eleman sayısını döndür
    override fun getItemCount(): Int = recipes.size

    // Listeyi güncellemek için bir fonksiyon
    fun updateRecipes(newRecipes: List<Recipe>) {
        recipes = newRecipes // Yeni listeyle güncelle
        notifyDataSetChanged() // Adapter'e değişiklik olduğunu bildir
    }
}
