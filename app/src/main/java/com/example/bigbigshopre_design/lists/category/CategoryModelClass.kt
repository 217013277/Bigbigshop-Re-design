package com.example.bigbigshopre_design.lists.category

//val CATEGORY_ID_EXTRA = "categoryExtra"

data class CategoryModelClass(
    var categories: ArrayList<Category>
)

data class Category (
    val id: Int,
    val title: String,
    val parent: List<String>
)

