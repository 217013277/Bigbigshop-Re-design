package com.example.bigbigshopre_design.lists.product

var productList = mutableListOf<Product>()

val PRODUCT_ID_EXTRA = "productExtra"

class Product (
    var cover: Int,
    var brand: String,
    var name: String,
    var price: String,
    var original: String,
    val id: Int? = productList.size
)

