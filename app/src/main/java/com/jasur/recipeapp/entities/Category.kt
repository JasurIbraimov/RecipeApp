package com.jasur.recipeapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Int,
    // Display Name
    @ColumnInfo(name="displayName")
    @Expose
    @SerializedName("displayName")
    val displayName: String,
    // Tag
    @ColumnInfo(name="tag")
    @Expose
    @SerializedName("tag")
    val tag: String,
    // Icon image
    @ColumnInfo(name="iconImage")
    @Expose
    @SerializedName("iconImage")
    val iconImage: String,
    )