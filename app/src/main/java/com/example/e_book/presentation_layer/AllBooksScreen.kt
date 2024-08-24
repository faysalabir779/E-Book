package com.example.e_book.presentation_layer

import androidx.compose.foundation.background
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState

@Composable
fun AllBooksScreen(modifier: Modifier = Modifier, viewModel: ViewModel = hiltViewModel()) {

    val pdfLink = remember {
        mutableStateOf("")
    }
    val res = viewModel.state.value

    if (pdfLink.value.isNullOrEmpty()) {
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

        if (res.items.isNotEmpty()) {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn {
                    items(res.items) {
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .clickable { pdfLink.value = it.bookUrl }) {
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
    else {
        val pdfState = rememberVerticalPdfReaderState(
            resource = ResourceType.Remote(pdfLink.value),
            isZoomEnable = true
        )
        VerticalPDFReader(
            state = pdfState,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Gray)
        )
    }

}