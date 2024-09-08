package com.example.e_book.presentation_layer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.e_book.presentation_layer.BooksByCategory
import com.example.e_book.presentation_layer.ShowPdfScreen
import com.example.e_book.presentation_layer.TabScreen

@Composable
fun NavGraphController(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Navigation.HomeScreen){
        composable<Navigation.HomeScreen> {
            TabScreen(navController)
        }
        composable<Navigation.BookByCategory> {
            val category = it.toRoute<Navigation.BookByCategory>()
            BooksByCategory(category = category.category, navController = navController)
        }
        composable<Navigation.ShowPdfScreen> {
            val pdf = it.toRoute<Navigation.ShowPdfScreen>()
            ShowPdfScreen(url = pdf.url)
        }
    }
}