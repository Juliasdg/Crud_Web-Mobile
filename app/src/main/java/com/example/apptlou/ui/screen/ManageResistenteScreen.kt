package com.example.apptlou.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.apptlou.ui.components.DrawerContent
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun ManageResistenteScreen(navController: NavController, resistenteId: String) {
    val resistente = remember { mutableStateOf<Resistente?>(null) }
    val isLoading = remember { mutableStateOf(true) }
    val firestore = FirebaseFirestore.getInstance()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    LaunchedEffect(resistenteId) {
        firestore.collection("resistentes").document(resistenteId)
            .get()
            .addOnSuccessListener { document ->
                val fetchedResistente = document.toObject(Resistente::class.java)
                resistente.value = fetchedResistente
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
                            if (isLoading.value) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Carregando resistente...",
                                        color = Color.White,
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                            } else {
                                val resistenteData = resistente.value
                                resistenteData?.let {
                                    Column(modifier = Modifier.fillMaxSize()) {
                                        Text(
                                            text = it.nome,
                                            style = MaterialTheme.typography.displayMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = Color(0xFFFF6666)
                                            ),
                                            modifier = Modifier.padding(bottom = 8.dp)
                                        )

                                        Text(
                                            text = "Idade: ${it.idade}",
                                            style = MaterialTheme.typography.bodyLarge.copy(
                                                fontStyle = FontStyle.Italic,
                                                color = Color.White
                                            ),
                                            modifier = Modifier.padding(bottom = 8.dp)
                                        )

                                        Text(
                                            text = "Especialidade(s): ${it.especialidade}",
                                            style = MaterialTheme.typography.bodyLarge.copy(
                                                fontStyle = FontStyle.Italic,
                                                color = Color.White
                                            ),
                                            modifier = Modifier.padding(bottom = 8.dp)
                                        )

                                        Text(
                                            text = "Localização: ${it.localizacao}",
                                            style = MaterialTheme.typography.bodyLarge.copy(
                                                fontStyle = FontStyle.Italic,
                                                color = Color.White
                                            ),
                                            modifier = Modifier.padding(bottom = 8.dp)
                                        )

                                        Text(
                                            text = "Mensagem: ${it.observacao}",
                                            style = MaterialTheme.typography.bodyLarge.copy(
                                                fontStyle = FontStyle.Italic,
                                                color = Color.White
                                            ),
                                            modifier = Modifier.padding(bottom = 16.dp)
                                        )

                                        Button(
                                            onClick = {
                                                navController.navigate("editarResistente/$resistenteId")
                                            },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(bottom = 8.dp),
                                            colors = ButtonDefaults.buttonColors(containerColor = Color(
                                                0xFF1FB227
                                            )
                                            ),
                                            shape = RoundedCornerShape(8.dp)
                                        ) {
                                            Text("Editar Resistente", color = Color.White)
                                        }

                                        Button(
                                            onClick = {
                                                firestore.collection("resistentes").document(resistenteId)
                                                    .delete()
                                                    .addOnSuccessListener {
                                                        navController.popBackStack()
                                                    }
                                            },
                                            modifier = Modifier.fillMaxWidth(),
                                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6666)),
                                            shape = RoundedCornerShape(8.dp)
                                        ) {
                                            Text("Excluir Resistente", color = Color.White)
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
