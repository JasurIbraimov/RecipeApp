package com.jasur.recipeapp.retrofitmodels.recipes

import com.google.gson.annotations.SerializedName

data class RecipeNutritionEstimateModel (
    @SerializedName("attribute")
    val attribute: String,
    @SerializedName("value")
    val value: Int,
    @SerializedName("unit")
    val unit: HashMap<*, *> = hashMapOf<String, String>("plural" to "", "abbreviation" to "" )
)
