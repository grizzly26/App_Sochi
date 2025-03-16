package com.example.app_sochi.ui.navigation



import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.app_sochi.ui.screens.MainScreen
import com.example.app_sochi.ui.screens.FishDescriptionScreen


@Composable
fun NavigationSetup(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "mainScreen") {
        composable("mainScreen") {
            MainScreen(navController)
        }
        composable("fishDescription/{fish}") { backStackEntry ->
            val fish = backStackEntry.arguments?.getString("fish") ?: ""
            FishDescriptionScreen(fish = fish)
        }
    }
}
