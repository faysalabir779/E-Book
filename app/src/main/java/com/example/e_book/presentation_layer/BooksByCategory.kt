package com.example.e_book.presentation_layer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Bookmarks
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.e_book.presentation_layer.component.BookCard
import com.example.e_book.presentation_layer.navigation.Navigation
import com.example.e_book.presentation_layer.viewModel.ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksByCategory(
    category: String,
    subCategory: String,
    viewModel: ViewModel = hiltViewModel(),
    navController: NavHostController
) {
    LaunchedEffect(key1 = true) {
        viewModel.loadBooksByCategory(category, subCategory)
    }

    val res = viewModel.state.value

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = category) }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Rounded.ArrowBack, contentDescription = null, tint = Color.Black)
            }
        })
    }){
        Column (modifier = Modifier.fillMaxSize().padding(it)){
            if (res.isLoading) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator()
                }
            }
            if (res.error.isNotEmpty()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(text = res.error)

                }
            }

            if (res.books.isNotEmpty()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    LazyColumn {
                        items(res.books) {
                            BookCard(
                                navController = navController,
                                bookImage = it.bookImage,
                                bookName = it.bookName,
                                bookAuthor = it.bookAuthor,
                                bookDescription = it.bookDescription,
                                bookUrl = it.bookUrl
                            )
                        }
                    }

                }
            }

        }
    }


}