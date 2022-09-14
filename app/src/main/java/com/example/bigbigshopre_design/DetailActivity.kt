package com.example.bigbigshopre_design

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bigbigshopre_design.databinding.ActivityDetailBinding
import com.example.bigbigshopre_design.lists.product.PRODUCT_ID_EXTRA
import com.example.bigbigshopre_design.lists.product.Product

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val productId = intent.getIntExtra(PRODUCT_ID_EXTRA, -1)
//        Log.i(TAG, productId.toString())
//        val product = productFromID(productId)
//        if (product != null) {
////            binding.cover.setImageResource(product.image)
//            binding.productDetailBrand.text = product.brand
//            binding.productDetailName.text = product.name
//            binding.productDetailPrice.text = product.salesPrice.toString()
//            binding.productDetailOriginal.text = product.originalPrice.toString()
//        }
    }

//    private fun productFromID(productId: Int): Product? {
//        for (product in productList) {
//            if (product.id == productId) return product
//        }
//        return null
//    }
}