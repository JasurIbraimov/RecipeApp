package com.jasur.recipeapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jasur.recipeapp.R
import com.jasur.recipeapp.entities.CategoryTopic
import com.jasur.recipeapp.retrofitmodels.CategoryTopicModel
import com.squareup.picasso.Picasso

class MainCategoryAdapter: RecyclerView.Adapter<MainCategoryAdapter.MainCategoryViewHolder>() {
    var categoryTopics = ArrayList<CategoryTopic>()

    class MainCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView = itemView.findViewById(R.id.categoryName)
        val categoryImage: ImageView = itemView.findViewById(R.id.categoryImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainCategoryViewHolder {
        return MainCategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_category_rvi, parent, false))
    }

    override fun onBindViewHolder(holder: MainCategoryViewHolder, position: Int) {
        val category = categoryTopics[position]
        holder.categoryName.text = category.displayName
        Picasso.with(holder.categoryImage.context).load(category.iconImage).into(holder.categoryImage)
    }

    override fun getItemCount(): Int {
        return categoryTopics.size
    }

    fun addCategories(categoryTopics: List<CategoryTopic>) {
        this.categoryTopics.apply {
            clear()
            addAll(categoryTopics)
        }

    }
}