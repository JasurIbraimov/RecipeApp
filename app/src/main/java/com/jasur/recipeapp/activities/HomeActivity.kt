package com.jasur.recipeapp.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jasur.recipeapp.R
import com.jasur.recipeapp.adapters.CategoryRecipesAdapter
import com.jasur.recipeapp.adapters.MainCategoryAdapter
import com.jasur.recipeapp.db.CategoriesDb
import com.jasur.recipeapp.entities.CategoryTopic
import com.jasur.recipeapp.repositories.CategoryTopicRepository
import com.jasur.recipeapp.retrofitclient.RetrofitClientInstance
import com.jasur.recipeapp.retrofitmodels.categories.AllCategoriesModel
import com.jasur.recipeapp.retrofitmodels.recipes.MainRecipeResponseModel
import com.jasur.recipeapp.retrofitmodels.recipes.RecipeModel
import com.jasur.recipeapp.vmodels.CategoryTopicViewModel
import com.jasur.recipeapp.vmodels.CategoryTopicViewModelFactory
import retrofit2.Call
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    private lateinit var categoryTopicViewModel: CategoryTopicViewModel
    private lateinit var mainCategoryAdapter: MainCategoryAdapter
    private lateinit var recipesAdapter: CategoryRecipesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val dao = CategoriesDb.getDatabase(application).categoriesDao()
        val repository = CategoryTopicRepository(dao)
        val factory = CategoryTopicViewModelFactory(repository)
        categoryTopicViewModel = ViewModelProvider(this,factory)[CategoryTopicViewModel::class.java]
        val mainCategoryList: RecyclerView = findViewById(R.id.main_category_list)
        val categoryItems: RecyclerView = findViewById(R.id.category_items)
        recipesAdapter = CategoryRecipesAdapter()
        mainCategoryAdapter = MainCategoryAdapter()
        mainCategoryList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        mainCategoryList.adapter = mainCategoryAdapter

        categoryItems.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        categoryItems.adapter = recipesAdapter

        categoryTopicViewModel.categoryTopics.observe(this) { categoryTopic ->
            categoryTopic.find { it.displayName == "Cake" }
                ?.let { retrieveList(categoryTopic, it.recipes) }
        }

    }

    override fun onStart() {
        categoryTopicViewModel.categoryTopics.observe(this) {
            if (it.isEmpty()) {
                getDataFromApi()
            }
        }
        super.onStart()
    }

    private fun getDataFromApi() {
        val call = RetrofitClientInstance.apiService.getAllCategories()
        call.enqueue(object : retrofit2.Callback<AllCategoriesModel> {
            override  fun onResponse(
                call: Call<AllCategoriesModel>,
                response: Response<AllCategoriesModel>
            ) {
                response.body()?.let { categories ->
                    for (data in categories.browseCategories[categories.browseCategories.size - 1].display.categoryTopics) {
                        val secondCall = RetrofitClientInstance.apiService.getRecipesByCategoryTag(data.display.tag)
                        Log.i("TAG", data.display.tag)
                        secondCall.enqueue(object : retrofit2.Callback<MainRecipeResponseModel> {
                            override fun onResponse(
                                call: Call<MainRecipeResponseModel>,
                                response: Response<MainRecipeResponseModel>
                            ) {
                                response.body()?.let {
                                    val categoryTopic = CategoryTopic(trackingId = data.trackingId,
                                        iconImage = data.display.iconImage,
                                        displayName = data.display.displayName,
                                        tag = data.display.tag,
                                        id = 0,
                                        recipes = it.feed
                                    )
                                    categoryTopicViewModel.insert(categoryTopic)
                                }
                            }

                            override fun onFailure(
                                call: Call<MainRecipeResponseModel>,
                                t: Throwable
                            ) {
                                Toast.makeText(applicationContext, "error fetch recipes", Toast.LENGTH_LONG).show()
                            }

                        })

                    }
                }
            }

            override fun onFailure(call: Call<AllCategoriesModel>, t: Throwable) {
                Toast.makeText(applicationContext, "error fetch categories", Toast.LENGTH_LONG).show()
            }

        })
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun retrieveList(categoryTopics: List<CategoryTopic>, recipes: List<RecipeModel>) {
        mainCategoryAdapter.apply {
            addCategories(categoryTopics)
            notifyDataSetChanged()
        }
        recipesAdapter.apply {
            setCategoryRecipes(recipes)
            notifyDataSetChanged()
        }

    }
}