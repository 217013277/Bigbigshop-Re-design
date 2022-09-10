package com.example.bigbigshopre_design.lists.cartProduct


interface CartProductClickListener {
    fun onClick(cartProduct: CartProduct)
    fun onSelect(id:Int)
    fun onDelete(id: Int)
}