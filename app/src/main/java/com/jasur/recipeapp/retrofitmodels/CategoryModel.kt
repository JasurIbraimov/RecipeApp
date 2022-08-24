package com.jasur.recipeapp.retrofitmodels

import com.google.gson.annotations.SerializedName
import com.jasur.recipeapp.entities.Category

data class CategoryModel (
    @SerializedName("promoted")
    val promoted: Boolean,
    @SerializedName("tracking-id")
    val trackingId: String,
    @SerializedName("display")
    val display: CategoryDisplayModel,
    @SerializedName("content")
    val content: HashMap<*, *> = hashMapOf<Any, Any>(),
    @SerializedName("type")
    val type: String,

)