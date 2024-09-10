package com.example.e_book.presentation_layer

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.e_book.data_layer.room.BookmarkModel
import com.example.e_book.presentation_layer.navigation.PdfViewerViewModel
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowPdfScreen(
    url: String,
    bookPage: Int = 0,
    bookImage: String,
    bookAuthor: String,
    bookName: String,
    viewModel: PdfViewerViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val pdfState = rememberVerticalPdfReaderState(
        resource = ResourceType.Remote(url),
        isZoomEnable = true
    )
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = bookName)
            },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Rounded.ArrowBackIosNew, contentDescription = null)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        viewModel.addBookmark(
                            BookmarkModel(
                                bookName = bookName,
                                bookAuthor = bookAuthor,
                                bookImage = bookImage,
                                bookUrl = url,
                                bookPage = pdfState.currentPage
                            )
                        )
                    }) {
                        Icon(Icons.Rounded.Bookmark, contentDescription = null)
                    }
                })
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            VerticalPDFReader(
                state = pdfState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Gray)
            )

        }


    }


}