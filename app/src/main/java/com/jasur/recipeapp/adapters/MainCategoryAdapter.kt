package com.jasur.recipeapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jasur.recipeapp.R
import com.jasur.recipeapp.entities.Recipe

class MainCategoryAdapter: RecyclerView.Adapter<MainCategoryAdapter.MainCategoryViewHolder>() {
    var mainCategories = ArrayList<Recipe>()

    class MainCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView = itemView.findViewById(R.id.categoryName)
        val categoryImage: ImageView = itemView.findViewById(R.id.categoryImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainCategoryViewHolder {
        return MainCategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_category_rvi, parent, false))
    }

    override fun onBindViewHolder(holder: MainCategoryViewHolder, position: Int) {
        val recipe = mainCategories[position]
        holder.categoryName.text = recipe.recipe_name
    }

    override fun getItemCount(): Int {
        return mainCategories.size
    }

    fun setCategories(categories: List<Recipe>) {
        mainCategories = categories as ArrayList<Recipe>
    }
}