package com.example.bigbigshopre_design.lists.breadcrumb

var breadcrumbList = arrayListOf<BreadcrumbModelClass>()

data class BreadcrumbModelClass (
    val name: String,
    val id: Int? = breadcrumbList.size
        )