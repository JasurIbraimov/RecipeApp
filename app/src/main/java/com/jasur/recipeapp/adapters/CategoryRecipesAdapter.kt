package com.jasur.recipeapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jasur.recipeapp.R
import com.jasur.recipeapp.retrofitmodels.recipes.RecipeModel
import com.squareup.picasso.Picasso

class CategoryRecipesAdapter: RecyclerView.Adapter<CategoryRecipesAdapter.CategoryRecipesViewHolder>() {
    var categoryRecipes = ArrayList<RecipeModel>()

    class CategoryRecipesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeName: TextView = itemView.findViewById(R.id.recipe_name)
        val recipeImage: ImageView = itemView.findViewById(R.id.recipe_image)
        val recipeTime: TextView = itemView.findViewById(R.id.recipe_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryRecipesViewHolder {
        return CategoryRecipesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recipe_item_rvi, parent, false))
    }

    override fun onBindViewHolder(holder: CategoryRecipesViewHolder, position: Int) {
        val recipe = categoryRecipes[position]
        holder.recipeName.text = recipe.display.displayName
        Picasso.with(holder.recipeImage.context).load(recipe.display.images[0]).into(holder.recipeImage)
        holder.recipeTime.text = recipe.content.details.totalTime
    }

    override fun getItemCount(): Int {
        return categoryRecipes.size
    }

    fun setCategoryRecipes(cRecipes: List<RecipeModel>) {
        categoryRecipes = cRecipes as ArrayList<RecipeModel>
    }
}