package com.example.spacetraveler.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.spacetraveler.data.cache.database.dao.TravelDao
import com.example.spacetraveler.data.cache.model.CacheTransaction

@Database(entities = [CacheTransaction::class], version = 1)
abstract class TravelDatabase : RoomDatabase() {
    abstract fun travelDao(): TravelDao
}