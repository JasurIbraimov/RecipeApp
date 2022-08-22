package com.jasur.recipeapp.repositories

import androidx.annotation.WorkerThread
import com.jasur.recipeapp.dao.RecipeDao
import com.jasur.recipeapp.entities.Recipe
import kotlinx.coroutines.flow.Flow

class RecipeRepository(private val recipeDao: RecipeDao) {
    val allRecipes: Flow<List<Recipe>> = recipeDao.getRecipes()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(recipe: Recipe) {
        recipeDao.insert(recipe)
    }
}