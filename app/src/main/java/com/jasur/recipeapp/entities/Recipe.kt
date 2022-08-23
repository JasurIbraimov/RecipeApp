package com.jasur.recipeapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "recipe_name")
    var recipe_name: String
)