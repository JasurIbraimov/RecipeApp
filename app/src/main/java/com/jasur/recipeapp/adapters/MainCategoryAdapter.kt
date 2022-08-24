package com.jasur.recipeapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jasur.recipeapp.R
import com.jasur.recipeapp.entities.Category
import com.jasur.recipeapp.entities.Recipe
import com.jasur.recipeapp.retrofitmodels.CategoryModel
import com.jasur.recipeapp.retrofitmodels.CategoryTopicModel
import com.squareup.picasso.Picasso

class MainCategoryAdapter: RecyclerView.Adapter<MainCategoryAdapter.MainCategoryViewHolder>() {
    var mainCategories = ArrayList<CategoryTopicModel>()

    class MainCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView = itemView.findViewById(R.id.categoryName)
        val categoryImage: ImageView = itemView.findViewById(R.id.categoryImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainCategoryViewHolder {
        return MainCategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_category_rvi, parent, false))
    }

    override fun onBindViewHolder(holder: MainCategoryViewHolder, position: Int) {
        val category = mainCategories[position]
        holder.categoryName.text = category.display.displayName
        Picasso.with(holder.categoryImage.context).load(category.display.iconImage).into(holder.categoryImage)
    }

    override fun getItemCount(): Int {
        return mainCategories.size
    }

    fun addCategories(categories: List<CategoryTopicModel>) {
        this.mainCategories.apply {
            clear()
            addAll(categories)
        }

    }
}