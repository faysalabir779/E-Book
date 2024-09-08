package com.example.e_book.presentation_layer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.e_book.presentation_layer.navigation.Navigation

@Composable
fun AllBooksScreen(modifier: Modifier = Modifier, viewModel: ViewModel = hiltViewModel(), navController: NavHostController) {

    LaunchedEffect(key1 = true) {
        viewModel.loadBooks()
    }

    val res = viewModel.state.value

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
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {navController.navigate(Navigation.ShowPdfScreen(it.bookUrl))  }) {
                        Row {
                            Text(text = it.bookName)
                            Text(text = it.bookUrl)
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

        }
    }

}