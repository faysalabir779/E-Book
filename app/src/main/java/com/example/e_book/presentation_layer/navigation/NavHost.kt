package com.example.e_book.presentation_layer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.e_book.presentation_layer.BookmarksScreen
import com.example.e_book.presentation_layer.BooksByCategory
import com.example.e_book.presentation_layer.MainUi
import com.example.e_book.presentation_layer.ShowPdfScreen
import com.example.e_book.presentation_layer.SubCategoryScreen

@Composable
fun NavGraphController(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Navigation.HomeScreen) {
        composable<Navigation.HomeScreen> {
            MainUi(navController)
        }
        composable<Navigation.BookByCategory> {
            val category = it.toRoute<Navigation.BookByCategory>()
            BooksByCategory(category = category.category, navController = navController)
        }

        composable<Navigation.BookMarkScreen> {
            BookmarksScreen(navController = navController)
        }
        composable<Navigation.ShowPdfScreen> {
            val pdf = it.toRoute<Navigation.ShowPdfScreen>()
            ShowPdfScreen(
                url = pdf.bookurl,
                pdf.bookPage,
                pdf.bookImage,
                pdf.bookAuthor,
                pdf.bookName,
                navController = navController
            )
        }

        composable<Navigation.SubCategoryScreen> {
            val categoryName = it.toRoute<Navigation.SubCategoryScreen>()
            SubCategoryScreen(navController = navController , categoryName = categoryName.categoryName)
        }
    }
}