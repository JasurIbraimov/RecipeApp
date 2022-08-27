package com.jasur.recipeapp.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jasur.recipeapp.retrofitmodels.recipes.RecipeModel

class RecipeModelConverter  {
        @TypeConverter
        fun convertListToString(recipes: List<RecipeModel>): String {
            val gson = Gson()
            val type = object : TypeToken<List<RecipeModel>>() {
            }.type
            return gson.toJson(recipes, type)
        }

        @TypeConverter
        fun convertStringToList(recipes: String): List<RecipeModel> {
            val gson = Gson()
            val type = object : TypeToken<List<RecipeModel>>(){

            }.type
            return gson.fromJson(recipes, type)
        }
}