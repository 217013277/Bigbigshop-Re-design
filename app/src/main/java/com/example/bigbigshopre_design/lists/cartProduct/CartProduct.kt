package com.example.bigbigshopre_design.lists.cartProduct

var cartProductList = mutableListOf<CartProduct>()

val CARTPRODUCT_ID_EXTRA = "cartProductExtra"

class CartProduct (
    var cover: Int,
    var brand: String,
    var name: String,
    var price: Int,
    var original: Int,
    var quantity: Int = 1,
    var isCheck: Boolean = true,
    val id: Int? = cartProductList.size
)