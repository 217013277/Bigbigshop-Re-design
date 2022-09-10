package com.example.bigbigshopre_design.lists.cartProduct

import android.content.ContentValues.TAG
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.bigbigshopre_design.databinding.CartProductCellBinding


class CartProductViewHolder (
    private val cartProductCellBinding: CartProductCellBinding,
    private val clickListener: CartProductClickListener
    ): RecyclerView.ViewHolder(cartProductCellBinding.root) {
        fun bindCartProduct(cartProduct: CartProduct) {
            cartProductCellBinding.cartProductImage.setImageResource(cartProduct.cover)
            cartProductCellBinding.brand.text = cartProduct.brand
            cartProductCellBinding.name.text = cartProduct.name
            cartProductCellBinding.price.text = cartProduct.price
            cartProductCellBinding.original.text = cartProduct.original

            cartProductCellBinding.quantity.text = cartProduct.quantity.toString()

            cartProductCellBinding.checkBox.isChecked = cartProduct.isCheck

            cartProductCellBinding.cartProductEditButton.setOnClickListener {
                cartProduct.id?.let { item -> clickListener.onEdit(item) }
            }

            cartProductCellBinding.checkBox.setOnClickListener {
                cartProduct.id?.let { item -> clickListener.onSelect(item) }
            }

            cartProductCellBinding.add.setOnClickListener{
                cartProduct.quantity += 1
                cartProductCellBinding.quantity.text = cartProduct.quantity.toString()
            }

            cartProductCellBinding.deduct.setOnClickListener{
                cartProduct.quantity -= 1
                Log.d(TAG,cartProduct.quantity.toString())
                if (cartProduct.quantity <= 0) {
                    cartProduct.id?.let { item -> clickListener.onDelete(item) }
                } else {
                    cartProductCellBinding.quantity.text = cartProduct.quantity.toString()
                }
            }
        }
    }