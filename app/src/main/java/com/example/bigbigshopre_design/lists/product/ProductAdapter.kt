package com.example.bigbigshopre_design.lists.product

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bigbigshopre_design.databinding.ProductCellBinding


class ProductAdapter(
    val context: Context,
    private var products: ArrayList<Product>,
    private val clickListener: ProductClickListener
    ) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = ProductCellBinding.inflate(from, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
//        holder.bindProduct(products[position])
        val listPosition = position
        val product = products[position]

        val path = context.resources.getIdentifier(product.image,"drawable",context.packageName)

        holder.image.setImageResource(path)
        holder.image.contentDescription = product.name
        holder.brand.text = product.brand
        holder.name.text = product.name
        holder.salesPrice.text = product.salesPrice.toString()
        holder.originalPrice.text = product.originalPrice.toString()

        holder.card.setOnClickListener { clickListener.onClick(product) }

    }

    override fun getItemCount(): Int = products.size

    class ProductViewHolder(
        binding: ProductCellBinding
    ): RecyclerView.ViewHolder(binding.root) {

        val image = binding.cover
        val brand = binding.brand
        val name = binding.name
        val salesPrice = binding.price
        val originalPrice = binding.original

        val card = binding.cardView

    }

    fun searchList(searchResultArrayList: ArrayList<Product>) {
        // below line is to add our filtered
        // list in our course array list.
        products = searchResultArrayList
        // below line is to notify our adapter
        // as change in recycler view data.

        notifyDataSetChanged()
    }
}