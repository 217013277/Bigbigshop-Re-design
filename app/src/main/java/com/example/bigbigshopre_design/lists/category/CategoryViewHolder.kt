package com.example.bigbigshopre_design.lists.category

import androidx.recyclerview.widget.RecyclerView
import com.example.bigbigshopre_design.databinding.CategoryCellBinding

class CategoryViewHolder(
    private val categoryCellBinding: CategoryCellBinding,
    private val clickListener: CategoryClickListener
    ): RecyclerView.ViewHolder(categoryCellBinding.root) {
    fun bindCategory(categoryDataModel: Category) {
        categoryCellBinding.categoryTitle.text = categoryDataModel.title

        categoryCellBinding.categoryCardView.setOnClickListener{
            clickListener.onClick(categoryDataModel)
        }
    }
}