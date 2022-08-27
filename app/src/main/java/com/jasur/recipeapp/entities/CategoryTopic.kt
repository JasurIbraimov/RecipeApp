package com.jasur.recipeapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.jasur.recipeapp.retrofitmodels.recipes.RecipeModel

@Entity(tableName = "category_topic")
data class CategoryTopic(
    @PrimaryKey(autoGenerate = true) val id: Int,
    // Tag
    @ColumnInfo(name="tracking-id")
    @Expose
    @SerializedName("tracking-id")
    val trackingId: String,
    // Icon image
    @ColumnInfo(name="icon-image")
    @Expose
    @SerializedName("iconImage")
    val iconImage: String,

    @ColumnInfo(name="displayName")
    @Expose
    @SerializedName("displayName")
    val displayName: String,
    // Tag
    @ColumnInfo(name="tag")
    @Expose
    @SerializedName("tag")
    val tag: String,

    @ColumnInfo(name="recipes")
    @Expose
    @SerializedName("recipes")
    val recipes: List<RecipeModel>
)