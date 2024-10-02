package com.example.e_book.common

data class BookModel (
    val bookUrl: String="",
    val bookName: String="",
    val bookAuthor: String="",
    val bookImage: String="",
    val bookDescription: String="",
    val category: String = "",
    val subCategory: String = ""
)

data class BookCategoryModel(
    val name: String = "",
    val categoryImageUrl: String = "",
    val subCategory: List<SubCategory> = emptyList()
)

data class SubCategory(
    val name: String = "",
)
