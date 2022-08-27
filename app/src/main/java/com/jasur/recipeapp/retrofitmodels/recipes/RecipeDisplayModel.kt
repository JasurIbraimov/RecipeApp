package com.jasur.recipeapp.retrofitmodels.recipes

import com.google.gson.annotations.SerializedName

data class RecipeDisplayModel (
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("flag")
    val flag: String
)