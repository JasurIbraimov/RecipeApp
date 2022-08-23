package com.jasur.recipeapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jasur.recipeapp.R
import com.jasur.recipeapp.adapters.CategoryRecipesAdapter
import com.jasur.recipeapp.adapters.MainCategoryAdapter
import com.jasur.recipeapp.entities.Recipe

class HomeActivity : AppCompatActivity() {

    private val categories = ArrayList<Recipe>()
    private val recipes = ArrayList<Recipe>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val mainCategoryList: RecyclerView = findViewById(R.id.main_category_list)
        val categoryItems: RecyclerView = findViewById(R.id.category_items)
        val categoryRecipesAdapter = CategoryRecipesAdapter()
        val mainCategoryAdapter = MainCategoryAdapter()
        // Temp data
        categories.add(Recipe(1, "Beef Meat"))
        categories.add(Recipe(2, "Chicken Meat"))
        categories.add(Recipe(3, "Fish Meat"))
        mainCategoryAdapter.setCategories(categories)
        mainCategoryList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
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
}