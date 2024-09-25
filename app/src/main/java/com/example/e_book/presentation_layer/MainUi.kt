package com.example.e_book.presentation_layer

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.rounded.Bookmarks
import androidx.compose.material.icons.rounded.BugReport
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.NoteAdd
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Shop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.e_book.R
import com.example.e_book.presentation_layer.navigation.Navigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainUi(navController: NavHostController) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val urlHandler = LocalUriHandler.current
    val context = LocalContext.current


    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.65f)
                        .padding(16.dp), verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp)
                                .align(Alignment.CenterHorizontally),
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.logo),
                                    contentDescription = "App Logo",
                                    modifier = Modifier
                                        .size(100.dp)
                                        .align(Alignment.CenterHorizontally)
                                    // .background(color = Color.White)
                                )
                            }

                        }

                        Spacer(modifier = Modifier.height(16.dp))
                        NavigationDrawerItem(
                            label = { Text("Home") },
                            selected = true,
                            icon = {
                                Icon(
                                    imageVector = Icons.Filled.Home,
                                    contentDescription = "Home"
                                )
                            },
                            onClick = {
                                scope.launch(Dispatchers.IO) {
                                    drawerState.close()
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(7.dp))
                        NavigationDrawerItem(
                            label = { Text("Version 1.0") },
                            selected = false,
                            icon = {
                                Icon(
                                    imageVector = Icons.Filled.Info,
                                    contentDescription = "Version 1.0"
                                )
                            },
                            onClick = {
                                scope.launch(Dispatchers.IO) {
                                    drawerState.close()
                                }
                                Toast.makeText(context, "Version 1.0", Toast.LENGTH_SHORT).show()

                            }
                        )

                        NavigationDrawerItem(
                            label = { Text("Contact Me") },
                            selected = false,
                            icon = {
                                Icon(
                                    imageVector = Icons.Filled.Chat,
                                    contentDescription = "Contact Me"
                                )
                            },
                            onClick = { urlHandler.openUri("https://www.linkedin.com/in/faysalabir779/") }
                        )
                        NavigationDrawerItem(
                            label = { Text("Source Code") },
                            selected = false,
                            icon = {
                                Icon(
                                    Icons.Rounded.Shop,
                                    contentDescription = "Source Code",
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            onClick = { scope.launch(Dispatchers.IO) {
                                drawerState.close()
                            } }
                        )
                        NavigationDrawerItem(
                            label = { Text("Bug Report") },
                            selected = false,
                            icon = {
                                Icon(
                                    Icons.Rounded.BugReport,
                                    contentDescription = "Source Code",
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            onClick = { scope.launch(Dispatchers.IO) {
                                drawerState.close()
                            } }
                        )
                    }

                    Column {
                        NavigationDrawerItem(
                            label = { Text("Share") },
                            selected = false,
                            icon = {
                                Icon(
                                    Icons.Rounded.Share,
                                    contentDescription = "Share",
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            onClick = { scope.launch(Dispatchers.IO) {
                                drawerState.close()
                            } })
                    }
                }
            }
        }) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = { Text(text = "Book Mart") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch(Dispatchers.IO) { drawerState.open() } }) {
                            Icon(Icons.Rounded.Menu, contentDescription = null)
                        }
                    },
                    actions = {
                        IconButton(onClick = { navController.navigate(Navigation.BookMarkScreen) }) {
                            Icon(Icons.Rounded.Bookmarks, contentDescription = null)
                        }
                    }, scrollBehavior = scrollBehavior
                )
            }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                TabScreen(navController = navController)
            }
        }

    }


}