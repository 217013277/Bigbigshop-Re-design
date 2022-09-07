package com.example.bigbigshopre_design.lists.category

import androidx.recyclerview.widget.RecyclerView
import com.example.bigbigshopre_design.databinding.CategoryCellBinding

class CategoryViewHolder(
    private val categoryCellBinding: CategoryCellBinding,
    private val clickListener: CategoryClickListener
    ): RecyclerView.ViewHolder(categoryCellBinding.root) {
    fun bindCategory(category: Category) {
        categoryCellBinding.categoryTitle.text = category.title

        categoryCellBinding.categoryCardView.setOnClickListener{
            clickListener.onClick(category)
        }
    }
}