package com.example.bigbigshopre_design.lists.cartProduct


interface CartProductClickListener {
    fun onClick(cartProduct: CartProduct)
    fun onEdit(id:Int)
    fun onSelect(id:Int)
    fun onDelete(id: Int)
    fun onChange()
}