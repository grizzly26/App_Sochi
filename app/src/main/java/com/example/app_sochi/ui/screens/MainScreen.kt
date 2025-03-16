package com.example.app_sochi.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.app_sochi.ui.components.CategoryList
import com.example.app_sochi.ui.components.DrawerContent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("MutableCollectionMutableState")
@Composable
fun MainScreen(navController: NavHostController) {
    // Categories and elements for fish
    val categories = listOf("Избранное") + listOf("Акула", "Скат", "Окунь", "Лосось", "Щука", "Тунец", "Карп", "Сом")
    val elements = mapOf(
        "Акула" to listOf("Белая акула", "Тигровая акула", "Молотоголовая акула"),
        "Скат" to listOf("Электрический скат", "Манта", "Шиповатый скат"),
        "Окунь" to listOf("Речной окунь", "Морской окунь"),
        "Лосось" to listOf("Атлантический лосось", "Чавыча", "Горбуша"),
        "Щука" to listOf("Обыкновенная щука", "Амурская щука"),
        "Тунец" to listOf("Жёлтый тунец", "Большеглазый тунец", "Синий тунец"),
        "Карп" to listOf("Зеркальный карп", "Чешуйчатый карп", "Кои"),
        "Сом" to listOf("Европейский сом", "Канальный сом", "Плоскоголовый сом")
    )

    var selectedCategory by remember { mutableStateOf(categories.first()) }
    var favorites by remember { mutableStateOf(setOf<String>()) } // Список избранных рыб
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    // Используем ModalNavigationDrawer с белым фоном
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            // Устанавливаем белый фон для DrawerContent
            Box(modifier = Modifier.background(Color.White)) {
                DrawerContent(
                    categories = categories,
                    selectedCategory = selectedCategory,
                    onCategorySelected = { category ->
                        selectedCategory = category
                        coroutineScope.launch {
                            drawerState.close()
                        }
                    },
                    modifier = Modifier.background(Color.Gray) // Здесь передаем модификатор
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Мой справочник рыб") },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Open drawer")
                        }
                    }
                )
            },
            content = { padding ->
                NavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        val displayedElements = if (selectedCategory == "Избранное") {
                            favorites.toList() // Если категория избранное, отображаем только избранных
                        } else {
                            elements[selectedCategory].orEmpty()
                        }

                        CategoryList(
                            selectedCategory = selectedCategory,
                            elements = displayedElements,
                            favorites = favorites,
                            onFishClick = { fish -> navController.navigate("fishDescription/$fish") },
                            onFavoriteToggle = { fish ->
                                val updatedFavorites = if (favorites.contains(fish)) {
                                    favorites - fish // Убираем рыбу из избранного
                                } else {
                                    favorites + fish // Добавляем рыбу в избранное
                                }
                                favorites = updatedFavorites
                            },
                            modifier = Modifier.padding(padding)
                        )
                    }
                    composable("fishDescription/{fish}") { backStackEntry ->
                        val fish = backStackEntry.arguments?.getString("fish") ?: ""
                        if (fish.isNotEmpty()) {
                            FishDescriptionScreen(fish = fish)
                        } else {
                            // Обработка ошибки, если рыба не найдена
                            Text("Рыба не найдена")
                        }
                    }
                }
            }
        )
    }
}
