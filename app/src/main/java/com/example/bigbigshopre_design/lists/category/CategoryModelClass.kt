package com.example.bigbigshopre_design.lists.category

//var categoryList = mutableListOf<Category>()

val CATEGORY_ID_EXTRA = "categoryExtra"

data class Categories(
//    val products: ArrayList<ProductModelClass>
    var categoryList: ArrayList<CategoryModelClass>
)

data class CategoryModelClass (
//    var title: String,
//    val id: Int? = categoryList.size

    val id: Int,
    val title: String,
    val parent: List<String>
)

