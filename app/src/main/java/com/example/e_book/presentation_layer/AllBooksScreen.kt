package com.example.e_book.presentation_layer

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
import androidx.compose.material3.Text
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


@Composable
fun AllBooksScreen(
    modifier: Modifier = Modifier,
    viewModel: ViewModel = hiltViewModel(),
    navController: NavHostController
) {

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(resId = R.raw.anim)
    )
    LaunchedEffect(key1 = true) {
        viewModel.loadBooks()
    }

    val res = viewModel.state.value

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
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(10.dp))
            LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
                items(res.books) {
                    BookCard(
                        navController,
                        it.bookImage,
                        it.bookName,
                        it.bookAuthor,
                        it.bookDescription,
                        it.bookUrl
                    )
                }
            }

        }
    }

}