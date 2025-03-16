package com.example.app_sochi.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Surface
import androidx.compose.ui.text.font.FontWeight

@Composable
fun DrawerContent(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Заголовок "Категории"
        Text(
            text = "Категории",
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        )

        categories.forEach { category ->
            val isSelected = category == selectedCategory

            // Каждый элемент в меню
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { onCategorySelected(category) }
                    .background(
                        color = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                        else Color.Transparent
                    )
                    .padding(12.dp),
                shape = MaterialTheme.shapes.medium, // Закругленные углы
                color = Color.Transparent
            ) {
                Text(
                    text = category,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
