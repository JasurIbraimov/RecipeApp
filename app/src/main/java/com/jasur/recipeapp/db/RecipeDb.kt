package com.jasur.recipeapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jasur.recipeapp.dao.RecipeDao
import com.jasur.recipeapp.entities.Recipe

@Database(entities = [Recipe::class], version = 1, exportSchema = false)
abstract class RecipeDb : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    companion object {
        private var INSTANCE: RecipeDb? = null
        fun getDatabase(context: Context): RecipeDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDb::class.java,
                    "recipe_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}