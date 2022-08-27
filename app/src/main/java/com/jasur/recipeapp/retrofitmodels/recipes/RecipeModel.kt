package com.jasur.recipeapp.retrofitmodels.recipes

import com.google.gson.annotations.SerializedName

data class RecipeModel (
    @SerializedName("display")
    val display: RecipeDisplayModel,
    @SerializedName("content")
    val content: RecipeContentModel,

)