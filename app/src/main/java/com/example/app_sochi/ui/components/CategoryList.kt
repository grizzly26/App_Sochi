package com.example.app_sochi.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_sochi.ui.utils.getImageForFish

@Composable
fun CategoryList(
    selectedCategory: String,
    elements: List<String>,
    favorites: Set<String>,
    onFishClick: (String) -> Unit,
    onFavoriteToggle: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var currentFavorites by remember { mutableStateOf(favorites) }

    Column(modifier = modifier) {
        // Заголовок категории с улучшенным стилем
        Text(
            text = "Выбрана категория: $selectedCategory",
            modifier = Modifier.padding(16.dp),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(elements) { element ->
                val isFavorite = currentFavorites.contains(element)

                // Карточка элемента с красивым оформлением
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(
                            Brush.linearGradient(
                                listOf(Color(0xFFE0F7FA), Color(0xFF80DEEA))
                            )
                        )
                        .border(2.dp, Color.Gray, shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
                        .clickable { onFishClick(element) }
                        .padding(8.dp)
                        .shadow(4.dp, shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
                        .clip(androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        // Картинка рыбы с плавными углами
                        Image(
                            painter = painterResource(id = getImageForFish(element)),
                            contentDescription = element,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .clip(androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Название рыбы с красивым фоном
                        Box(
                            modifier = Modifier
                                .background(Color(0xFF0288D1))
                                .fillMaxWidth()
                                .padding(16.dp)
                                .clip(androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
                        ) {
                            Text(
                                text = element,
                                style = TextStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                ),
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Кнопка для добавления в избранное
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            IconButton(
                                onClick = {
                                    val newFavorites = currentFavorites.toMutableSet()
                                    if (isFavorite) {
                                        newFavorites.remove(element)
                                    } else {
                                        newFavorites.add(element)
                                    }
                                    currentFavorites = newFavorites
                                    onFavoriteToggle(element)
                                },
                                modifier = Modifier
                                    .padding(8.dp)
                                    .align(Alignment.TopEnd)
                                    .background(Color.White, shape = androidx.compose.foundation.shape.CircleShape)
                                    .border(1.dp, Color.Gray, shape = androidx.compose.foundation.shape.CircleShape)
                            ) {
                                Icon(
                                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                    contentDescription = "Избранное",
                                    tint = if (isFavorite) Color.Red else Color.Gray
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
