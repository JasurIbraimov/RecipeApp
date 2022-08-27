package com.jasur.recipeapp.vmodels

import androidx.lifecycle.*
import com.jasur.recipeapp.entities.CategoryTopic
import com.jasur.recipeapp.repositories.CategoryTopicRepository
import kotlinx.coroutines.launch

class CategoryTopicViewModel(private val categoryTopicRepository: CategoryTopicRepository): ViewModel() {
    val categoryTopics: LiveData<List<CategoryTopic>> = categoryTopicRepository.categoryTopics.asLiveData()

    fun insert(categoryTopic: CategoryTopic) = viewModelScope.launch {
        categoryTopicRepository.insert(categoryTopic)
    }
    
}
