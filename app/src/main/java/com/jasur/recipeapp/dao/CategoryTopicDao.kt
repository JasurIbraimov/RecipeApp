package com.jasur.recipeapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jasur.recipeapp.entities.CategoryTopic
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryTopicDao {
    @Query("SELECT * FROM category_topic")
    fun getCategoryTopics(): Flow<List<CategoryTopic>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categoryTopic: CategoryTopic)

    @Query("DELETE from category_topic")
    fun deleteAll()

}