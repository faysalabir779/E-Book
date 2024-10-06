package com.example.e_book.presentation_layer

import android.widget.Space
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.e_book.presentation_layer.component.CategoryCard
import com.example.e_book.presentation_layer.viewModel.ViewModel

@Composable
fun CategoryScreen(viewModel: ViewModel = hiltViewModel(), navController: NavHostController) {
    LaunchedEffect(key1 = true) {
        viewModel.loadCategory()
    }
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(resId = R.raw.anim)
    )
    val res = viewModel.state.value
    if (res.isLoading){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            LottieAnimation(
                composition = composition,
                modifier = Modifier.size(190.dp),
                iterations = LottieConstants.IterateForever
            )
        }
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