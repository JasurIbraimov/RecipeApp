package com.jasur.recipeapp.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jasur.recipeapp.entities.Category

class CategoriesConverter {
    @TypeConverter
    fun fromCategoriesListToString(categoriesList: List<Category>): String? {
        val gson = Gson()
        val type = object : TypeToken<Category>() {

        }.type
        return gson.toJson(categoriesList, type)
    }

    @TypeConverter
    fun toCategoriesListFromString(categoriesString: String): List<Category>? {
        val gson = Gson()
        val type = object : TypeToken<Category>(){

        }.type
        return gson.fromJson(categoriesString, type)
    }
}