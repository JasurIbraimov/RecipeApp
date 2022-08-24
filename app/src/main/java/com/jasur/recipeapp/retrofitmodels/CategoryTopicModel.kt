package com.jasur.recipeapp.retrofitmodels

import com.google.gson.annotations.SerializedName
import com.jasur.recipeapp.entities.Category

data class CategoryTopicModel(
    // Display Name
    @SerializedName("promoted")
    val promoted: Boolean,
    // Tag
    @SerializedName("tracking-id")
    val trackingId: String,
    // Icon image
    @SerializedName("iconImage")
    val iconImage: String,

    @SerializedName("display")
    val display: Category,

    @SerializedName("content")
    val content: HashMap<*, *> = hashMapOf<Any, Any>(),
    @SerializedName("type")
    val type: String,
)