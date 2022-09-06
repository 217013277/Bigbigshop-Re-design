package com.example.bigbigshopre_design

var bookList = mutableListOf<Book>()

class Book (
    var cover: Int,
    var author: String,
    var title: String,
    var description: String,
    val id: Int? = bookList.size
)

