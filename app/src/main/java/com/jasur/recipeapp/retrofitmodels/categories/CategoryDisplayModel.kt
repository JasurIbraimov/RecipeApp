package com.jasur.recipeapp.retrofitmodels.categories

import com.google.gson.annotations.SerializedName

data class CategoryDisplayModel (
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("iconImage")
    val iconImage: String,
    @SerializedName("categoryImage")
    val categoryImage: String,
    @SerializedName("categoryTopics")
    val categoryTopics: List<CategoryTopicModel>,
    )