package com.example.e_book.presentation_layer.navigation

import kotlinx.serialization.Serializable

sealed class Navigation {
    @Serializable
    object HomeScreen

    @Serializable
    data class BookByCategory(val category: String)

    @Serializable
    data class ShowPdfScreen(val url: String)
}