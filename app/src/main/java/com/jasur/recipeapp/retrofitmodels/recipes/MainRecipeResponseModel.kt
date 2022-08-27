package com.jasur.recipeapp.retrofitmodels.recipes

import com.google.gson.annotations.SerializedName


data class MainRecipeResponseModel(
    @SerializedName("feed")
    val feed: List<RecipeModel>,
    @SerializedName("seo")
    val seo: HashMap<*, *> = hashMapOf<Any, Any>()
)