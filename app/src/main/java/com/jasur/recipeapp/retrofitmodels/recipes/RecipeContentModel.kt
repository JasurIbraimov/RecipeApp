package com.jasur.recipeapp.retrofitmodels.recipes

import com.google.gson.annotations.SerializedName

data class RecipeContentModel (
    @SerializedName("details")
    val details: RecipeContentDetailModel,
    @SerializedName("ingredientLines")
    val ingredientLines: List<RecipeIngredientModel>,
    @SerializedName("reviews")
    val reviews: RecipeReviewsModel,
    @SerializedName("nutrition")
    val nutrition: HashMap<String, *> = hashMapOf("mobileSectionName" to "",
        "nutritionEstimates" to ArrayList<RecipeNutritionEstimateModel>()),
    )