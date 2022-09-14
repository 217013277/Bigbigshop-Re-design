package com.example.bigbigshopre_design.lists.category

//val CATEGORY_ID_EXTRA = "categoryExtra"

data class CategoryDataClass(
    var categories: ArrayList<CategoryModelClass>
)

data class CategoryModelClass (
    val id: Int,
    val title: String,
    val parent: List<String>
)

