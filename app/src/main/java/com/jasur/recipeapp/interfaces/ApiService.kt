package com.jasur.recipeapp.interfaces

import com.jasur.recipeapp.retrofitmodels.AllCategoriesModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
    @Headers( "X-RapidAPI-Key: d18dc4a589msh81e4f9f630963afp1e8768jsn0f90a84d0bb7",  "X-RapidAPI-Host': 'yummly2.p.rapidapi.com")
    @GET("categories/list")
    fun getAllCategories(): Call<AllCategoriesModel>

}