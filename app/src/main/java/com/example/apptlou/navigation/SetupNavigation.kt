package com.example.apptlou.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.apptlou.ui.screen.EasterEggsScreen
import com.example.apptlou.ui.screen.HomeScreen
import com.example.apptlou.ui.screen.ListScreen
import com.example.apptlou.ui.screen.LoginScreen
import com.example.apptlou.ui.screen.ManageResistenteScreen
import com.example.apptlou.ui.screen.PersonagensScreen
import com.example.apptlou.ui.screen.ResistenciaAddScreen
import com.example.apptlou.ui.screen.ResistenciaEditScreen
import com.example.apptlou.ui.screen.SoundtrackScreen

@Composable
fun SetupNavigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "home") {
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("personagens") {
            PersonagensScreen(navController = navController)
        }
        composable("easter_eggs") {
            EasterEggsScreen(navController = navController)
        }
        composable("soundtrack"){
            SoundtrackScreen(navController)
        }
        composable("resistencia") {
            ResistenciaAddScreen(navController = navController)
        }
        composable("consulta") {
            ListScreen(navController = navController)
        }
        composable("manageResistente/{resistenteId}") { backStackEntry ->
            val resistenteId = backStackEntry.arguments?.getString("resistenteId")
            ManageResistenteScreen(navController = navController, resistenteId = resistenteId.toString())
        }
        composable("editarResistente/{resistenteId}"){backStackEntry ->
            val resistenteId = backStackEntry.arguments?.getString("resistenteId")
            ResistenciaEditScreen(navController = navController, resistenteId = resistenteId.toString())
        }
    }
}