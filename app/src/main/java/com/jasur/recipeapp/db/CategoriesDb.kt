package com.jasur.recipeapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jasur.recipeapp.converter.RecipeModelConverter
import com.jasur.recipeapp.dao.CategoryTopicDao
import com.jasur.recipeapp.entities.CategoryTopic

@Database(entities = [CategoryTopic::class], version = 1, exportSchema = false)
@TypeConverters(RecipeModelConverter::class)
abstract class CategoriesDb : RoomDatabase() {
    abstract fun categoriesDao(): CategoryTopicDao
    companion object {
        private var INSTANCE: CategoriesDb? = null
        fun getDatabase(context: Context): CategoriesDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CategoriesDb::class.java,
                    "categories_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}