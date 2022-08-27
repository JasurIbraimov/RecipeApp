package com.jasur.recipeapp.retrofitmodels

import com.google.gson.annotations.SerializedName

data class AllCategoriesModel(
    @SerializedName("browse-categories")
    val browseCategories: List<CategoryModel>,
    @SerializedName("shopping-categories")
    val shoppingCategories: List<CategoryModel>,
)