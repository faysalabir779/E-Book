package com.example.e_book.common

data class BookModel (
    val bookUrl: String="",
    val bookName: String="",
    val bookAuthor: String="",
    val bookImage: String="",
    val bookDescription: String="",
    val category: String=""
)

data class BookCategoryModel(
    val Name: String = ""
)