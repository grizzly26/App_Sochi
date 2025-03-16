package com.example.app_sochi



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.app_sochi.ui.screens.MainScreen


import com.example.app_sochi.ui.theme.GuideAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuideAppTheme {
                val navController = rememberNavController() as NavHostController
                MainScreen(navController = navController)
            }
        }
    }
}