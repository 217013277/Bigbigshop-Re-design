package com.example.bigbigshopre_design.lists.book

var bookList = mutableListOf<Book>()

const val BOOK_ID_EXTRA = "bookExtra"

class Book (
    var cover: Int,
    var author: String,
    var title: String,
    var description: String,
    val id: Int? = bookList.size
)

