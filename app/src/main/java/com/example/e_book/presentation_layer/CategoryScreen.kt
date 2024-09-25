package com.example.e_book.presentation_layer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.e_book.presentation_layer.component.CategoryCard
import com.example.e_book.presentation_layer.viewModel.ViewModel

@Composable
fun CategoryScreen(viewModel: ViewModel = hiltViewModel(), navController: NavHostController) {
    LaunchedEffect(key1 = true) {
        viewModel.loadCategory()
    }
    val res = viewModel.state.value
    if (res.isLoading){
        CircularProgressIndicator()
    }
    if (res.error.isNotEmpty()){
        Text(text = res.error)
    }
    if (res.category.isNotEmpty()){
        Box(modifier = Modifier.fillMaxSize()) {
            LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
                items(res.category) {
                    CategoryCard(it.categoryImageUrl, it.name, navController)
                }
            }

        }
    }
}