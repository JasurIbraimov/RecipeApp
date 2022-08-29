package com.jasur.recipeapp.retrofitmodels.recipes

import com.google.gson.annotations.SerializedName

data class RecipeIngredientModel (
    @SerializedName("unit")
    val unit: String,
    @SerializedName("ingredientId")
    val ingredientId: String,
    @SerializedName("ingredient")
    val ingredient: String,
    @SerializedName("remainder")
    val remainder: String,
    @SerializedName("quantity")
    val quantity: Double
)