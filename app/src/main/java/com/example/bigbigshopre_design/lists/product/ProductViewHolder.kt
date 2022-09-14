package com.example.bigbigshopre_design.lists.product

import androidx.recyclerview.widget.RecyclerView
import com.example.bigbigshopre_design.databinding.ProductCellBinding

//class ProductViewHolder(
//    private val productCellBinding: ProductCellBinding,
//    private val clickListener: ProductClickListener
//    ): RecyclerView.ViewHolder(productCellBinding.root) {
//    fun bindProduct(product: Product) {
//        val path = context.resources.getIdentifier(product.image,"drawable",context.packageName)
////        productCellBinding.cover.setImageResource(product.image)
//        productCellBinding.brand.text = product.brand
//        productCellBinding.name.text = product.name
//        productCellBinding.price.text = product.salesPrice.toString()
//        productCellBinding.original.text = product.originalPrice.toString()
//
//        productCellBinding.cardView.setOnClickListener{
//            clickListener.onClick(product)
//        }
//    }
//}