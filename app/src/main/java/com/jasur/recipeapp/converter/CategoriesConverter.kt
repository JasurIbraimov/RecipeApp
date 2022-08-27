package com.jasur.recipeapp.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jasur.recipeapp.retrofitmodels.CategoryTopicDisplayModel

class CategoriesConverter {
    @TypeConverter
    fun convertListToString(categoriesList: List<CategoryTopicDisplayModel>): String? {
        if (categoriesList == null) {
            return null
        } else {
            val gson = Gson()
            val type = object : TypeToken<CategoryTopicDisplayModel>() {
            }.type
            return gson.toJson(categoriesList, type)
        }
    }

    @TypeConverter
    fun convertStringToList(categoriesString: String): List<CategoryTopicDisplayModel>? {
        if (categoriesString == null) {
            return null
        } else {
            val gson = Gson()
            val type = object : TypeToken<CategoryTopicDisplayModel>(){

            }.type
            return gson.fromJson(categoriesString, type)
        }
    }
}