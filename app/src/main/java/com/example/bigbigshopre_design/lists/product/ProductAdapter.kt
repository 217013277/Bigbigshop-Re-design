package com.example.bigbigshopre_design.lists.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bigbigshopre_design.databinding.CategoryCellBinding
import com.example.bigbigshopre_design.databinding.ProductCellBinding
import com.example.bigbigshopre_design.lists.category.Category
import com.example.bigbigshopre_design.lists.category.CategoryClickListener
import com.example.bigbigshopre_design.lists.category.CategoryViewHolder


class ProductAdapter(
    private val products: List<Product>,
    private val clickListener: ProductClickListener
    ) : RecyclerView.Adapter<ProductViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = ProductCellBinding.inflate(from, parent, false)
        return ProductViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindCategory(products[position])
    }

    override fun getItemCount(): Int = products.size
}