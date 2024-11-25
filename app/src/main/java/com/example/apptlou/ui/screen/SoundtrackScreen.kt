package com.example.apptlou.ui.screen

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.apptlou.R
import com.example.apptlou.ui.components.DrawerContent
import kotlinx.coroutines.launch

@SuppressLint("SetJavaScriptEnabled")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoundtrackScreen(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                DrawerContent(navController = navController, isDrawerOpen = drawerState.isOpen)
            },
            content = {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("") },
                            navigationIcon = {
                                IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                    Icon(Icons.Filled.Menu, contentDescription = "Abrir Menu")
                                }
                            },
                            colors = TopAppBarColors(
                                containerColor = Color(0xFF1B1B1B),
                                scrolledContainerColor = Color(0xFF1B1B1B),
                                navigationIconContentColor = Color.White,
                                titleContentColor = Color(0xFF1B1B1B),
                                actionIconContentColor = Color(0xFF1B1B1B),
                            )
                        )
                    },
                    content = { paddingValues ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(0xFF1B1B1B))
                                .padding(paddingValues)
                                .verticalScroll(rememberScrollState())
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(240.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.banner), // Substitua pela imagem do banner
                                    contentDescription = "Banner",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(
                                            Brush.verticalGradient(
                                                colors = listOf(
                                                    Color.Transparent,
                                                    Color(0xFF1B1B1B)
                                                ),
                                                startY = 100f
                                            )
                                        )
                                )
                                Column(
                                    modifier = Modifier
                                        .align(Alignment.BottomStart)
                                        .padding(16.dp)
                                ) {
                                    Text(
                                        text = "Soundtrack Oficial",
                                        color = Color(0xFFFF6666),
                                        style = MaterialTheme.typography.displayMedium.copy(
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    Spacer(modifier = Modifier.height(8.dp)) // Espaçamento entre título e subtítulo
                                    Text(
                                        text = "Aprecie a playlist oficial do game!",
                                        color = Color.White,
                                        style = MaterialTheme.typography.bodyLarge.copy(
                                            fontStyle = FontStyle.Italic
                                        )
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Spacer(modifier = Modifier.height(16.dp))
                                AndroidView(
                                    factory = { context ->
                                        WebView(context).apply {
                                            settings.javaScriptEnabled = true
                                            webViewClient =
                                                WebViewClient() // Para não abrir o link fora da WebView
                                            loadUrl("https://open.spotify.com/embed/playlist/6iNZ7Z6VLpGxVlLJfV5t2Z?utm_source=generator&theme=0")
                                        }
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                )

                                Spacer(modifier = Modifier.height(66.dp))

                                Image(
                                    painter = painterResource(id = R.drawable.soundtrack), // Substitua pela imagem do álbum
                                    contentDescription = "Imagem da Soundtrack",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(180.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    })
            })
    }
}



