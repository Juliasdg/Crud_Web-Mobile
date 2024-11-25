package com.example.apptlou.ui.screen

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apptlou.R
import com.example.apptlou.ui.components.DrawerContent
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {

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

                        var email by remember { mutableStateOf("") }
                        var password by remember { mutableStateOf("") }
                        var passwordVisible by remember { mutableStateOf(false) }
                        var loading by remember { mutableStateOf(false) }
                        var errorMessage by remember { mutableStateOf<String?>(null) }

                        fun isEmailValid(email: String): Boolean {
                            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
                        }

                        fun loginUser() {
                            if (email.isEmpty()) {
                                Toast.makeText(
                                    navController.context,
                                    "Por favor, insira seu e-mail.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return
                            }

                            if (password.isEmpty()) {
                                Toast.makeText(
                                    navController.context,
                                    "Por favor, insira sua senha.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return
                            }

                            if (!isEmailValid(email)) {
                                Toast.makeText(
                                    navController.context,
                                    "E-mail e/ou senha incorretos.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return
                            }

                            loading = true
                            errorMessage = null
                            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener { task ->
                                    loading = false
                                    if (task.isSuccessful) {
                                        navController.navigate("home")
                                    } else {
                                        val error = task.exception?.message ?: "Erro ao fazer login, verifique suas credenciais e tente novamente."

                                        when (task.exception) {
                                            is com.google.firebase.auth.FirebaseAuthInvalidCredentialsException -> {
                                                Toast.makeText(
                                                    navController.context,
                                                    "E-mail e/ou senha incorretos.",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                            is com.google.firebase.auth.FirebaseAuthInvalidUserException -> {
                                                Toast.makeText(
                                                    navController.context,
                                                    "Erro ao fazer login, verifique suas credenciais e tente novamente.",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                            is com.google.firebase.FirebaseNetworkException -> {
                                                Toast.makeText(
                                                    navController.context,
                                                    "Erro de conexão. Verifique sua rede e tente novamente.",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                            else -> {
                                                Toast.makeText(
                                                    navController.context,
                                                    error,
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                    }
                                }
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(0xFF1B1B1B))
                                .padding(paddingValues)
                        ) {
                            Column(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .width(350.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.tlou2_logo),
                                    contentDescription = "Logo",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .size(200.dp)
                                        .padding(bottom = 2.dp)
                                )

                                OutlinedTextField(
                                    value = email,
                                    onValueChange = { email = it },
                                    label = { Text("Email") },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 16.dp),
                                    singleLine = true,
                                    colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White)
                                )

                                OutlinedTextField(
                                    value = password,
                                    onValueChange = { password = it },
                                    label = { Text("Senha") },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 16.dp),
                                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                                    trailingIcon = {
                                        if (password.isNotEmpty()) {
                                            val image = if (passwordVisible)
                                                painterResource(id = R.drawable.ic_visibility_off)
                                            else
                                                painterResource(id = R.drawable.ic_visibility)

                                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                                Image(painter = image, contentDescription = "Mostrar Senha")
                                            }
                                        }
                                    },
                                    colors = OutlinedTextFieldDefaults.colors(focusedTextColor = Color.White, unfocusedTextColor = Color.White)
                                )

                                Button(
                                    onClick = { loginUser() },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(48.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Red,
                                        contentColor = Color.White
                                    ),
                                    enabled = !loading
                                ) {
                                    Text(if (loading) "Carregando..." else "Entrar")
                                }
                            }
                        }

                    })
            })
    }
}


