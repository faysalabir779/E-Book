package com.example.e_book.presentation_layer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.e_book.presentation_layer.component.BookCard
import com.example.e_book.presentation_layer.component.BookmarkBookCard
import com.example.e_book.presentation_layer.viewModel.BookMarkViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarksScreen(
    viewModel: BookMarkViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val bookmark by viewModel.bookmarks.collectAsState()

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "My Bookmark") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Rounded.ArrowBack, contentDescription = null, tint = Color.Black)
                }
            },
        )
    }) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            if (bookmark.isEmpty()){
                Text(text = "No Bookmark Found", modifier = Modifier.padding(it))
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(bookmark) { book ->
                BookmarkBookCard(
                    navController = navController,
                    bookImage = book.bookImage,
                    bookName = book.bookName,
                    bookAuthor = book.bookAuthor,
                    bookUrl = book.bookUrl,
                    bookPage = book.bookPage,
                    onDeleteClick = {
                        viewModel.deleteBook(book)
                    }
                )
            }
        }
    }
}