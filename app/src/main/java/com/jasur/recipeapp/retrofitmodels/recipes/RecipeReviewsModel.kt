package com.jasur.recipeapp.retrofitmodels.recipes

import com.google.gson.annotations.SerializedName

data class RecipeReviewsModel (
    @SerializedName("totalReviewCount")
    val totalReviewCount: Int,
    @SerializedName("averageRating")
    val averageRating: Double
)
