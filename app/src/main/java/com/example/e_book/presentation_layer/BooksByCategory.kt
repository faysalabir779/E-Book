package com.example.e_book.presentation_layer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.e_book.R
import com.example.e_book.presentation_layer.component.BookCard
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
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(resId = R.raw.anim)
    )
    val composition2 by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(resId = R.raw.empty_book)
    )

    val res = viewModel.state.value

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = subCategory) }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Rounded.ArrowBack, contentDescription = null)
            }
        })
    }) {
        if (res.books.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    LottieAnimation(composition = composition2, modifier = Modifier.size(280.dp), iterations = LottieConstants.IterateForever)
                    Text(text = "No Books Found\nOn this category", modifier = Modifier.padding(it))
                }
            }

        }
        if (res.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LottieAnimation(
                    composition = composition,
                    modifier = Modifier.size(190.dp),
                    iterations = LottieConstants.IterateForever
                )
            }
        }
        if (res.error.isNotEmpty()) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = res.error)
            }
        }

        if (res.books.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
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
                    Column(modifier = Modifier.fillMaxSize()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
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


}