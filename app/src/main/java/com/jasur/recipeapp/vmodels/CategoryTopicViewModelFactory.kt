package com.jasur.recipeapp.vmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jasur.recipeapp.repositories.CategoryTopicRepository

class CategoryTopicViewModelFactory(private val categoryTopicRepository: CategoryTopicRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryTopicViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CategoryTopicViewModel(categoryTopicRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}