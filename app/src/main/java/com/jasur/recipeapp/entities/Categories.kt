package com.jasur.recipeapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.jasur.recipeapp.converter.CategoriesConverter

@Entity(tableName = "categories")
data class Categories(
    @PrimaryKey(autoGenerate = true) val id: Int,
    // Display Name
    @ColumnInfo(name="dishes")
    @Expose
    @SerializedName("dishes")
    @TypeConverters(CategoriesConverter::class)
    val categories: List<Category>? = null
)