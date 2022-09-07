package com.example.bigbigshopre_design.lists.category

var categoryList = mutableListOf<Category>()

val CATEGORY_ID_EXTRA = "categoryExtra"

class Category (
    var title: String,
    val id: Int? = categoryList.size
)

