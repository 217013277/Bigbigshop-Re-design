package com.example.bigbigshopre_design.lists.category

import com.google.gson.annotations.SerializedName

//val CATEGORY_ID_EXTRA = "categoryExtra"

data class CategoryModelClass(
    @SerializedName("categories")
    var categoryList: ArrayList<Category>
)

data class Category (
    val id: Int,
    val title: String,
    val parent: List<String>
)

