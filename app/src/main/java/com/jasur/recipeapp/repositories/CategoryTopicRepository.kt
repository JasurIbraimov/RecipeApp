package com.jasur.recipeapp.repositories

import androidx.annotation.WorkerThread
import com.jasur.recipeapp.dao.CategoryTopicDao
import com.jasur.recipeapp.entities.CategoryTopic

class CategoryTopicRepository(private val categoryTopicDao: CategoryTopicDao) {
    val categoryTopics = categoryTopicDao.getCategoryTopics()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(categoryTopic: CategoryTopic) {
        return categoryTopicDao.insert(categoryTopic)
    }
    suspend fun deleteAll(){
        return categoryTopicDao.deleteAll()
    }
}