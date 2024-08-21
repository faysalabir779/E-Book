package com.example.e_book.presentation_layer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AllBooksScreen(modifier: Modifier = Modifier) {
    LazyColumn {
        items(10) {
            Text(text = "Books")
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}