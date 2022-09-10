package com.example.bigbigshopre_design.lists.cartProduct

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bigbigshopre_design.Cart
import com.example.bigbigshopre_design.databinding.CartProductCellBinding

class CartProductAdapter(
    private val cartProducts: List<CartProduct>,
    private val clickListener: CartProductClickListener
    ) : RecyclerView.Adapter<CartProductViewHolder>()  {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductViewHolder {
            val from = LayoutInflater.from(parent.context)
            val binding = CartProductCellBinding.inflate(from, parent, false)
            return CartProductViewHolder(binding, clickListener)
        }

        override fun onBindViewHolder(holder: CartProductViewHolder, position: Int) {
            holder.bindCartProduct(cartProducts[position])
        }

        override fun getItemCount(): Int = cartProducts.size
    }
