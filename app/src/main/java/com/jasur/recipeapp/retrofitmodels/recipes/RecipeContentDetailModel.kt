package com.jasur.recipeapp.retrofitmodels.recipes

import com.google.gson.annotations.SerializedName

data class RecipeContentDetailModel(
    @SerializedName("totalTime")
    val totalTime: String,
    @SerializedName("numberOfServings")
    val numberOfServings: Int,
    @SerializedName("recipeId")
    val recipeId: String
)