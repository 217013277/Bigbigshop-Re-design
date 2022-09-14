package com.example.bigbigshopre_design.lists.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bigbigshopre_design.databinding.CategoryCellBinding

class CategoryAdapter(
    private val categories: MutableList<CategoryModelClass>,
    private val clickListener: CategoryClickListener
    ) : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = CategoryCellBinding.inflate(from, parent, false)
        return CategoryViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindCategory(categories[position])
    }

    override fun getItemCount(): Int = categories.size
}