package com.jasur.recipeapp.retrofitmodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CategoryTopicDisplayModel(
    // Display Name
    @Expose
    @SerializedName("displayName")
    val displayName: String,
    // Tag
    @Expose
    @SerializedName("tag")
    val tag: String,
    // Icon image
    @Expose
    @SerializedName("iconImage")
    val iconImage: String,
    )