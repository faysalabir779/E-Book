package com.example.e_book.presentation_layer

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Category
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Preview(showBackground = true, showSystemUi = true)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabScreen() {
    var pagerState = rememberPagerState(pageCount = { 2 })
    Column {
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState)
    }


}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tabs(pagerState: PagerState) {
    val tabNames = arrayOf(
        tabItem("Category", Icons.Filled.Category),
        tabItem("All Books", Icons.Filled.Book)
    )
    val coroutine = rememberCoroutineScope()

    TabRow(selectedTabIndex = pagerState.currentPage) {
        tabNames.forEachIndexed { index, title ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutine.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                ){
                Spacer(modifier = Modifier.height(10.dp))
                Icon(imageVector = title.icon, contentDescription = null)
                Text(text = title.name)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState) {
        when(it){
            0 -> CategoryScreen()
            1 -> AllBooksScreen()
        }
    }

}

data class tabItem(
    val name: String,
    val icon: ImageVector
)
