package com.example.bigbigshopre_design.lists.product

var productList = mutableListOf<Product>()

val PRODUCT_ID_EXTRA = "productExtra"

class Product (
    var cover: Int,
    var author: String,
    var title: String,
    var description: String,
    val id: Int? = productList.size
)

