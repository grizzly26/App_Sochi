package com.example.app_sochi.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animals")
data class Fish(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,                // Имя животного
    val category: String,            // Категория животного (например, Львы, Тигры)
    val isFavorite: Boolean = false, // Поле для отслеживания, является ли животное в избранном
    val createdAt: Long = System.currentTimeMillis(), // Время добавления
    val imageResId: Int? = null      // Ресурс изображения животного
)
