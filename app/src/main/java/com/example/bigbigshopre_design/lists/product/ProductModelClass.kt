package com.example.bigbigshopre_design.lists.product

//var productList = mutableListOf<Product>()

val PRODUCT_ID_EXTRA = "productExtra"

data class ProductModelClass(
    var products: ArrayList<Product>
)

data class Product (
//    var cover: Int,
//    var brand: String,
//    var name: String,
//    var price: String,
//    var original: String,
//    val id: Int? = productList.size

    val id: Int,
    var image: String,
    var brand: String,
    var name: String,
    var salesPrice: Int,
    var originalPrice: Int,
    val categories: List<String>
)

