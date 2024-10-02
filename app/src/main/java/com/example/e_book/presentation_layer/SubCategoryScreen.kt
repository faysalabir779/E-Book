package com.example.e_book.presentation_layer

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.e_book.R
import com.example.e_book.presentation_layer.viewModel.ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubCategoryScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: ViewModel = hiltViewModel(),
    categoryName: String
) {

    LaunchedEffect(key1 = true) {
        viewModel.loadSubCategory(categoryName)
    }

    val subCategory = viewModel.state.value
    Log.d("subcategory", "SubCategoryScreen: ${subCategory.subCategory.size}")
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(resId = R.raw.anim)
    )

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = categoryName) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Rounded.ArrowBack, contentDescription = null, tint = Color.Black)
                }
            },
        )
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (subCategory.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    LottieAnimation(
                        composition = composition,
                        modifier = Modifier.size(190.dp),
                        iterations = LottieConstants.IterateForever
                    )
                }
            }
            if (subCategory.subCategory.isEmpty()) {
                CircularProgressIndicator()
            }
            if (subCategory.error.isNotEmpty()) {
                Text(text = subCategory.error)
            }
            if (subCategory.subCategory.isNotEmpty()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    LazyColumn (modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 15.dp)){
                        items(subCategory.subCategory) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .height(50.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Row {
                                    Text(text = it.name)
                                    Icon(Icons.Filled.ArrowForward, contentDescription = null)
                                }

                            }
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }

                }
            }

        }
    }
}