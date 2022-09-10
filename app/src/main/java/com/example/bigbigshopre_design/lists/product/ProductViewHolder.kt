package com.example.bigbigshopre_design.lists.product

import androidx.recyclerview.widget.RecyclerView
import com.example.bigbigshopre_design.databinding.ProductCellBinding

class ProductViewHolder(
    private val productCellBinding: ProductCellBinding,
    private val clickListener: ProductClickListener
    ): RecyclerView.ViewHolder(productCellBinding.root) {
    fun bindProduct(product: Product) {
        productCellBinding.cover.setImageResource(product.cover)
        productCellBinding.brand.text = product.brand
        productCellBinding.name.text = product.name
        productCellBinding.price.text = product.price
        productCellBinding.original.text = product.original

        productCellBinding.cardView.setOnClickListener{
            clickListener.onClick(product)
        }
    }
}