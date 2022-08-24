package com.jasur.recipeapp.retrofitmodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AllCategoriesModel(
    @SerializedName("browse-categories")
    val browseCategories: List<CategoryModel>,
    @SerializedName("shopping-categories")
    val shoppingCategories: List<CategoryModel>,
)