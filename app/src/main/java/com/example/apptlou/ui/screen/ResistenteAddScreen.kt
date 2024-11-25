package com.example.apptlou.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apptlou.data.Resistente
import com.example.apptlou.ui.components.DrawerContent
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResistenciaAddScreen(navController: NavController) {
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
                        var nome by remember { mutableStateOf("") }
                        var idade by remember { mutableStateOf("") }
                        var especialidade by remember { mutableStateOf("") }
                        var localizacao by remember { mutableStateOf("") }
                        var observacao by remember { mutableStateOf("") }

                        val context = LocalContext.current

                        fun saveResistentToFirestore(
                            resistente: Resistente,
                            onSuccess: (String) -> Unit,
                            onFailure: (Exception) -> Unit
                        ) {
                            val db = FirebaseFirestore.getInstance()
                            db.collection("resistentes")
                                .add(resistente)
                                .addOnSuccessListener { documentReference ->
                                    onSuccess(documentReference.id)
                                }
                                .addOnFailureListener { exception ->
                                    onFailure(exception)
                                }
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                                .background(Color(0xFF1B1B1B))
                                .padding(16.dp)
                                .verticalScroll(rememberScrollState())
                        ) {
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
                                        text = "Junte-se à Resistência",
                                        color = Color(0xFFFF6666),
                                        style = MaterialTheme.typography.displayMedium.copy(
                                            fontWeight = FontWeight.Bold
                                        ),
                                        modifier = Modifier.align(Alignment.CenterHorizontally)
                                    )
                                    Spacer(modifier = Modifier.height(8.dp)) // Espaçamento entre título e subtítulo
                                    Text(
                                        text = "Se você é um verdadeiro aliado e deseja lutar pela nossa causa, preencha suas informações abaixo. Caso seja aceito, não se preocupe... nós encontraremos você.",
                                        color = Color.White,
                                        style = MaterialTheme.typography.bodyLarge.copy(
                                            fontStyle = FontStyle.Italic
                                        ),
                                    )
                                }
                            }

                            Column(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .fillMaxWidth()
                                    .padding(top = 260.dp) // Começar abaixo do banner (ajuste o valor conforme necessário)
                            ) {
                                OutlinedTextField(
                                    value = nome,
                                    onValueChange = { nome = it },
                                    label = { Text("Digite seu nome") },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 16.dp),
                                    singleLine = true,
                                    colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White)
                                )

                                OutlinedTextField(
                                    value = idade,
                                    onValueChange = { idade = it },
                                    label = { Text("Insira sua idade") },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 16.dp),
                                    singleLine = true,
                                    keyboardOptions = KeyboardOptions.Default.copy(
                                        keyboardType = KeyboardType.Phone
                                    ),
                                    colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White)
                                )

                                OutlinedTextField(
                                    value = especialidade,
                                    onValueChange = { especialidade = it },
                                    label = { Text("Atirador, Combate corpo a corpo...") },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 16.dp),
                                    singleLine = true,
                                    colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White)
                                )

                                OutlinedTextField(
                                    value = localizacao,
                                    onValueChange = { localizacao = it },
                                    label = { Text("Após o envio do formulário, não saia de onde está (caso saia, diga para onde vai)") },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 16.dp),
                                    singleLine = true,
                                    colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White)
                                )

                                OutlinedTextField(
                                    value = observacao,
                                    onValueChange = { observacao = it },
                                    label = { Text("Deixe sua mensagem") },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 16.dp)
                                        .height(150.dp),
                                    singleLine = false,
                                    colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White)
                                )

                                Button(
                                    onClick = {
                                        if (nome.isBlank() || idade.isBlank() || especialidade.isBlank() || localizacao.isBlank() || observacao.isBlank()) {
                                            Toast.makeText(context, "Preencha todos os campos!", Toast.LENGTH_SHORT)
                                                .show()
                                            return@Button
                                        }

                                        val newResistente = Resistente(
                                            nome = nome,
                                            idade = idade,
                                            especialidade = especialidade,
                                            localizacao = localizacao,
                                            observacao = observacao
                                        )

                                        saveResistentToFirestore(
                                            resistente = newResistente,
                                            onSuccess = { documentId ->
                                                Toast.makeText(context, "Resistente adicionado!", Toast.LENGTH_SHORT)
                                                    .show()

                                                idade = ""
                                                especialidade = ""
                                                localizacao = ""
                                                observacao = ""
                                                navController.popBackStack()
                                            },
                                            onFailure = { exception ->
                                                Toast.makeText(
                                                    context,
                                                    "Erro ao salvar: ${exception.message}",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        )
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(68.dp)
                                        .padding(bottom = 16.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFFF6666),
                                        contentColor = Color.White
                                    ),
                                ) {
                                    Text("Enviar Solicitação")
                                }
                            }
                        }
                    })
            })
    }
}
