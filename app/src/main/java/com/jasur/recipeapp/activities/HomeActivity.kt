package com.jasur.recipeapp.activities
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
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
    private lateinit var homeScreen: LinearLayout
    lateinit var categoryName: TextView
    private lateinit var splashScreen: FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        splashScreen = findViewById(R.id.splashScreen)
        homeScreen = findViewById(R.id.homeScreen)
        categoryName = findViewById(R.id.categoryTitleName)

        homeScreen.visibility = View.INVISIBLE
        splashScreen.visibility = View.VISIBLE
        val getStartedBtn: Button = findViewById(R.id.get_started_btn)
        val progressBar: ProgressBar = findViewById(R.id.loader)
        val dao = CategoriesDb.getDatabase(application).categoriesDao()
        val repository = CategoryTopicRepository(dao)
        val factory = CategoryTopicViewModelFactory(repository)
        categoryTopicViewModel = ViewModelProvider(this,factory)[CategoryTopicViewModel::class.java]

        getStartedBtn.setOnClickListener { button ->
            button.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE
            categoryTopicViewModel.categoryTopics.observe(this) {
                if (it.isEmpty()) {
                    getDataFromApi()
                    Log.i("EMPTY", "Category topics are empty")
                } else {
                    Log.i("NOT EMPTY", "Category topics are not empty")
                    homeScreen.visibility = View.VISIBLE
                    splashScreen.visibility = View.INVISIBLE
                }
            }
        }

        val mainCategoryList: RecyclerView = findViewById(R.id.main_category_list)
        val categoryItems: RecyclerView = findViewById(R.id.category_items)
        recipesAdapter = CategoryRecipesAdapter()
        mainCategoryAdapter = MainCategoryAdapter()
        mainCategoryAdapter.context = this

        mainCategoryList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mainCategoryList.adapter = mainCategoryAdapter

        categoryItems.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        categoryItems.adapter = recipesAdapter
        categoryTopicViewModel.categoryTopics.observe(this) { categoryTopics ->
            updateCategoryAdapter(categoryTopics)
            updateRecipesAdapter(categoryTopics[0].recipes, categoryTopics[0].displayName)
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateCategoryAdapter(categoryTopics: List<CategoryTopic>) {
        Log.i("UPDATE CATEGORY ADAPTER", categoryTopics.toString())
        mainCategoryAdapter.apply {
            addCategories(categoryTopics)
            notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateRecipesAdapter(recipes: List<RecipeModel>, displayName: String) {
        recipesAdapter
            .apply {
            setCategoryRecipes(recipes)
            notifyDataSetChanged()
        }
        categoryName.text = displayName
    }

    private fun getDataFromApi() {
        val call = RetrofitClientInstance.apiService.getAllCategories()
        call.enqueue(object : retrofit2.Callback<AllCategoriesModel> {
            override  fun onResponse(
                call: Call<AllCategoriesModel>,
                response: Response<AllCategoriesModel>
            ) {
                response.body()?.let { categories ->
//                    val data = ArrayList<CategoryTopicModel>()
//                    data.add(categories.browseCategories[categories.browseCategories.size - 1].display.categoryTopics[0])
//                    data.add(categories.browseCategories[categories.browseCategories.size - 1].display.categoryTopics[1])
                    val data = categories.browseCategories[categories.browseCategories.size - 1].display.categoryTopics
                    var fetchedCount = 0
                    for (category in data) {
                        val secondCall = RetrofitClientInstance.apiService.getRecipesByCategoryTag(category.display.tag)
                        secondCall.enqueue(object : retrofit2.Callback<MainRecipeResponseModel> {
                            override fun onResponse(
                                call: Call<MainRecipeResponseModel>,
                                response: Response<MainRecipeResponseModel>
                            ) {
                                response.body()?.let {
                                    val categoryTopic = CategoryTopic(trackingId = category.trackingId,
                                        iconImage = category.display.iconImage,
                                        displayName = category.display.displayName,
                                        tag = category.display.tag,
                                        id = 0,
                                        recipes = it.feed
                                    )
                                    categoryTopicViewModel.insert(categoryTopic)
                                    Log.i("ADD", "ADDED TOPIC")
                                    fetchedCount++
                                    if (fetchedCount == data.size) {
                                        homeScreen.visibility = View.VISIBLE
                                        splashScreen.visibility = View.INVISIBLE
                                    }
                                }
                            }
                            override fun onFailure(
                                call: Call<MainRecipeResponseModel>,
                                t: Throwable
                            ) {
                                Log.i("Error!!!", t.message.toString())
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

}