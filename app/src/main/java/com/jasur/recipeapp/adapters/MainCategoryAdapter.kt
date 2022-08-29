package com.jasur.recipeapp.adapters

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.jasur.recipeapp.R
import com.jasur.recipeapp.activities.HomeActivity
import com.jasur.recipeapp.entities.CategoryTopic
import com.squareup.picasso.Picasso

class MainCategoryAdapter: RecyclerView.Adapter<MainCategoryAdapter.MainCategoryViewHolder>() {
    var categoryTopics = ArrayList<CategoryTopic>()
    lateinit var context: HomeActivity
    private var selectedPosition = 0

    class MainCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView = itemView.findViewById(R.id.categoryName)
        val categoryImage: ImageView = itemView.findViewById(R.id.categoryImage)
        val categoryCard: CardView = itemView.findViewById(R.id.categoryCard)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainCategoryViewHolder {
        return MainCategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_category_rvi, parent, false))
    }

    override fun onBindViewHolder(holder: MainCategoryViewHolder, position: Int) {
        val category = categoryTopics[position]
        holder.categoryName.text = category.displayName
        if(position == selectedPosition) {
            holder.categoryName.setBackgroundResource(R.color.primaryColor)
            val rotate = ObjectAnimator.ofFloat(holder.categoryName, View.ROTATION, 0f, 360f)
            rotate.duration = 300
            val scaleX = ObjectAnimator.ofFloat(holder.categoryCard, View.SCALE_X, 1f, 1.1f)
            scaleX.duration = 300
            val scaleY = ObjectAnimator.ofFloat(holder.categoryCard, View.SCALE_Y, 1f, 1.1f)
            scaleY.duration = 300
            val animation = AnimatorSet()
            animation.play(rotate).with(scaleX).with(scaleY)
            animation.start()
        } else {
            holder.categoryName.setBackgroundResource(R.color.transparentDark)
            holder.categoryName.rotation = 0f
            holder.categoryCard.scaleY = 1f
            holder.categoryCard.scaleX = 1f
        }
        Picasso.with(holder.categoryImage.context).load(category.iconImage).into(holder.categoryImage)
        holder.categoryCard.setOnClickListener {
            if (holder.adapterPosition== RecyclerView.NO_POSITION) return@setOnClickListener;
            val categoryTopic = requireNotNull(categoryTopics.find { categoryTopic ->  categoryTopic.displayName == category.displayName })
            context.updateRecipesAdapter(categoryTopic.recipes, categoryTopic.displayName)
            notifyItemChanged(selectedPosition);
            selectedPosition = holder.adapterPosition
            notifyItemChanged(selectedPosition)
        }
    }

    override fun getItemCount(): Int {
        return categoryTopics.size
    }
    fun addCategories(categoryTopics: List<CategoryTopic>) {
        this.categoryTopics.apply {
            clear()
            addAll(categoryTopics)
        }

    }

}