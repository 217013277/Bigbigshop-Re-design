package com.example.bigbigshopre_design.lists.product

import androidx.recyclerview.widget.RecyclerView
import com.example.bigbigshopre_design.databinding.ProductCellBinding

class ProductViewHolder(
    private val productCellBinding: ProductCellBinding,
    private val clickListener: ProductClickListener
    ): RecyclerView.ViewHolder(productCellBinding.root) {
    fun bindCategory(product: Product) {
//        cardCellBinding.position.text = category.id.toString()
        productCellBinding.title.text = product.title

        productCellBinding.cardView.setOnClickListener{
            clickListener.onClick(product)
        }
    }
}