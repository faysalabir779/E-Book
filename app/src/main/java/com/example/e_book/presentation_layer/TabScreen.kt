package com.example.e_book.presentation_layer

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.e_book.R
import kotlinx.coroutines.launch

@Preview(showBackground = true, showSystemUi = true)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabScreen(navController: NavHostController) {
    var pagerState = rememberPagerState(pageCount = { 2 })
    Column {
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState, navController)
    }


}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tabs(pagerState: PagerState) {
    val tabNames = arrayOf(
        tabItem(
            "All Books",
            painterResource(id = R.drawable.book)
        ),
        tabItem("Category", painterResource(id = R.drawable.category))
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
            ) {
                Spacer(modifier = Modifier.height(6.dp))
                Row(horizontalArrangement = Arrangement.Center) {
                    Icon(painter = title.icon, contentDescription = null, modifier = Modifier.size(25.dp))
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = title.name)
                }
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsContent(pagerState: PagerState, navController: NavHostController) {
    HorizontalPager(state = pagerState) {
        when (it) {
            0 -> AllBooksScreen(navController = navController)
            1 -> CategoryScreen(navController = navController)
        }
    }

}

data class tabItem(
    val name: String,
    val icon: Painter
)
