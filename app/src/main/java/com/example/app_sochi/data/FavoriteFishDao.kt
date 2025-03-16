package com.example.app_sochi.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AnimalDao {

    @Insert
    suspend fun insert(fish: Fish)

    @Query("SELECT * FROM animals")
    suspend fun getAllAnimals(): List<Fish>

    @Query("SELECT * FROM animals WHERE isFavorite = 1")
    suspend fun getFavoriteAnimals(): List<Fish> // Метод для получения только избранных животных

    @Update
    suspend fun updateAnimal(fish: Fish) // Метод для обновления данных о животном (например, для изменения статуса избранного)

    @Query("DELETE FROM animals WHERE id = :animalId")
    suspend fun deleteById(animalId: Int)
}
