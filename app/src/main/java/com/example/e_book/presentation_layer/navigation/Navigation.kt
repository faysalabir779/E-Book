package com.example.e_book.presentation_layer.navigation

import kotlinx.serialization.Serializable

sealed class Navigation {
    @Serializable
    object HomeScreen

    @Serializable
    data class BookByCategory(val category: String)

    @Serializable
    data class ShowPdfScreen(
        val bookName: String,
        val bookAuthor: String,
        val bookImage: String,
        val bookPage: Int = 0,
        val bookurl: String
    )
}