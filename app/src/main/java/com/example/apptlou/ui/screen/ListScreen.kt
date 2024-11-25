package com.example.apptlou.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apptlou.data.Resistente
import com.example.apptlou.ui.components.CardResistentes
import com.example.apptlou.ui.components.DrawerContent
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavController) {
    val resistentes = remember { mutableStateOf<List<Resistente>>(emptyList()) }
    val isLoading = remember { mutableStateOf(true) }
    val firestore = FirebaseFirestore.getInstance()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        firestore.collection("resistentes")
            .get()
            .addOnSuccessListener { result ->
                resistentes.value = result.mapNotNull { document ->
                    document.toObject(Resistente::class.java).copy(id = document.id)
                }
                isLoading.value = false
            }
            .addOnFailureListener {
                isLoading.value = false
            }
    }

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
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color(0xFF1B1B1B),
                                titleContentColor = Color.White,
                                navigationIconContentColor = Color.White
                            )
                        )
                    },
                    content = { paddingValues ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                                .background(Color(0xFF1B1B1B))
                                .padding(16.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                // Banner
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(240.dp)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .align(Alignment.BottomStart)
                                            .padding(16.dp)
                                    ) {
                                        Text(
                                            text = "Resistentes Cadastrados",
                                            color = Color(0xFFFF6666),
                                            style = MaterialTheme.typography.displayMedium.copy(
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = "Aqui estão os aliados que lutam pela causa.",
                                            color = Color.White,
                                            style = MaterialTheme.typography.bodyLarge.copy(
                                                fontStyle = FontStyle.Italic
                                            )
                                        )
                                    }
                                }

                                if (isLoading.value) {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "Carregando...",
                                            color = Color.White,
                                            style = MaterialTheme.typography.bodyLarge
                                        )
                                    }
                                } else {
                                    LazyColumn(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(top = 16.dp),
                                        contentPadding = paddingValues
                                    ) {
                                        items(resistentes.value) { resistente ->
                                            CardResistentes(
                                                resistente = resistente,
                                                navController = navController
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                )
            }
        )
    }
}
