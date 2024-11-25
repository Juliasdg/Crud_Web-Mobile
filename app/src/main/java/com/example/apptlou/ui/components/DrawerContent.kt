package com.example.apptlou.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun DrawerContent(navController: NavController, isDrawerOpen: Boolean) {
    val backgroundColor = if (isDrawerOpen) Color(0xFF1B1B1B) else Color.Transparent

    val currentUser = FirebaseAuth.getInstance().currentUser
    val isLoggedIn = currentUser != null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        Text(
            text = "Menu",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold, color = Color.White) // Título
        )
        Spacer(modifier = Modifier.height(16.dp))

        DrawerItem(text = "Sobre", onClick = { navController.navigate("home") })
        DrawerItem(text = "Personagens", onClick = { navController.navigate("personagens") })
        DrawerItem(text = "EasterEggs", onClick = { navController.navigate("easter_eggs") })
        DrawerItem(text = "Soundtrack", onClick = { navController.navigate("soundtrack") })
        DrawerItem(text = "Resistência", onClick = { navController.navigate("resistencia") })

        if (isLoggedIn) {
            DrawerItem(text = "Consultar", onClick = { navController.navigate("consulta") })
            DrawerItem(text = "Sair", onClick = {
                FirebaseAuth.getInstance().signOut()
                navController.navigate("login")
            })
        } else {
            DrawerItem(text = "Login", onClick = { navController.navigate("login") })
        }
    }
}
