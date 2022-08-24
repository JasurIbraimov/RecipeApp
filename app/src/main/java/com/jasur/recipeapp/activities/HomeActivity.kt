package com.jasur.recipeapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jasur.recipeapp.R
import com.jasur.recipeapp.adapters.CategoryRecipesAdapter
import com.jasur.recipeapp.adapters.MainCategoryAdapter
import com.jasur.recipeapp.entities.Recipe
import com.jasur.recipeapp.retrofitclient.RetrofitClientInstance
import com.jasur.recipeapp.retrofitmodels.AllCategoriesModel
import com.jasur.recipeapp.retrofitmodels.CategoryModel
import retrofit2.Call
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private val recipes = ArrayList<Recipe>()
    private lateinit var mainCategoryAdapter: MainCategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val mainCategoryList: RecyclerView = findViewById(R.id.main_category_list)
        val categoryItems: RecyclerView = findViewById(R.id.category_items)
        val categoryRecipesAdapter = CategoryRecipesAdapter()
        mainCategoryAdapter = MainCategoryAdapter()
        mainCategoryList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getDataFromApi()
        mainCategoryList.adapter = mainCategoryAdapter




        // Temp
        recipes.add(Recipe(1, "Easy Meatloaf"))
        recipes.add(Recipe(2, "Hamburger Steak with Onions and Gravy"))
        recipes.add(Recipe(3, "Tender Eye of Round Roast"))
        recipes.add(Recipe(4, "Mississippi Pot Roast"))
        recipes.add(Recipe(5, "World's Best Lasagna"))

        categoryRecipesAdapter.setCategoryRecipes(recipes)
        categoryItems.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        categoryItems.adapter = categoryRecipesAdapter



    }

    private fun getDataFromApi() {
        val call = RetrofitClientInstance.apiService.getAllCategories()
        call.enqueue(object : retrofit2.Callback<AllCategoriesModel> {
            override fun onResponse(
                call: Call<AllCategoriesModel>,
                response: Response<AllCategoriesModel>
            ) {
                response.body()?.let { retrieveList(it.browseCategories.get(it.browseCategories.size - 1)) }
            }

            override fun onFailure(call: Call<AllCategoriesModel>, t: Throwable) {
                Toast.makeText(applicationContext, "error", Toast.LENGTH_LONG).show()
            }

        })
    }
    private fun retrieveList(category: CategoryModel) {
        mainCategoryAdapter.apply {
            addCategories(category.display.categoryTopics)
            notifyDataSetChanged()
        }
    }
}