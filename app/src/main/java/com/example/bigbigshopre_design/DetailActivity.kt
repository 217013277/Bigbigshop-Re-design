package com.example.bigbigshopre_design

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bigbigshopre_design.databinding.ActivityDetailBinding
import com.example.bigbigshopre_design.lists.product.PRODUCT_ID_EXTRA
import com.example.bigbigshopre_design.lists.product.Product
import com.example.bigbigshopre_design.lists.product.productList

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productId = intent.getIntExtra(PRODUCT_ID_EXTRA, -1)
        Log.i(TAG, productId.toString())
        val product = productFromID(productId)
        if (product != null) {
            binding.cover.setImageResource(product.cover)
            binding.title.text = product.name
            binding.author.text = product.brand
            binding.description.text = product.price
        }
    }

    private fun productFromID(productId: Int): Product? {
        for (product in productList) {
            if (product.id == productId) return product
        }
        return null
    }
}