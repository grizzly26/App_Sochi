package com.example.app_sochi.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Fish::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun animalDao(): AnimalDao
}
