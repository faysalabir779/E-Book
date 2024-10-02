package com.example.e_book.presentation_layer.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DeleteForever
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.SubcomposeAsyncImage
import com.example.e_book.presentation_layer.navigation.Navigation

@Composable
fun BookmarkBookCard(
    navController: NavHostController,
    bookImage: String,
    bookName: String,
    bookAuthor: String,
    bookUrl: String,
    bookPage: Int,
    onDeleteClick: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(0.94f)
                .clickable {
                    navController.navigate(
                        Navigation.ShowPdfScreen(
                            bookName,
                            bookAuthor,
                            bookImage,
                            bookurl = bookUrl
                        )
                    )
                },
        ) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(10.dp)) {
                        SubcomposeAsyncImage(
                            model = bookImage,
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                            modifier = Modifier
                                .height(100.dp)
                                .width(65.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            error = {
                                Text(text = "Error")
                            },
                            loading = {
                                CircularProgressIndicator()
                            }
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(
                            modifier = Modifier.fillMaxHeight(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = bookName,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                maxLines = 2,
                                lineHeight = 17.sp,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = bookPage.toString(),
                                fontSize = 13.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Bold
                            )
                        }

                    }
                    Box(modifier = Modifier, contentAlignment = Alignment.CenterEnd) {
                        IconButton(onClick = onDeleteClick) {
                            Icon(
                                Icons.Rounded.DeleteForever,
                                contentDescription = null,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}