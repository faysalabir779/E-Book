package com.example.e_book.presentation_layer

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.e_book.data_layer.room.BookmarkModel
import com.example.e_book.presentation_layer.navigation.PdfViewerViewModel
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
        isZoomEnable = true,
        isAccessibleEnable = true
    )
    var isLoading by remember { mutableStateOf(false) }
    var loadingProgress by remember { mutableStateOf(0f) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(pdfState.isLoaded) {
        scope.launch(Dispatchers.IO) {
            if (pdfState.isLoaded) {
                isLoading = false
                loadingProgress = 1f
                pdfState.currentPage
                pdfState.isScrolling
            } else {
                launch {
                    while (!pdfState.isLoaded && loadingProgress < 0.9f) {
                        delay(100)
                        loadingProgress += 0.01f
                    }
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = bookName, maxLines = 2, overflow = TextOverflow.Ellipsis, fontSize = 19.sp, lineHeight = 22.sp)
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
                        Toast.makeText(context, "Bookmarks Added", Toast.LENGTH_SHORT).show()
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
                    .background(color = MaterialTheme.colorScheme.background)
            )

            if (!pdfState.isLoaded) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center)
                ) {
                    CircularProgressIndicator(
                        progress = { loadingProgress },
                        modifier = Modifier.matchParentSize(),
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Text(
                        text = "${(loadingProgress * 100).toInt()}%",
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

        }


    }


}