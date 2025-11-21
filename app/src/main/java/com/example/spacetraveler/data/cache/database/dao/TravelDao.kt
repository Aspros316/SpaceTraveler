package com.example.spacetraveler.data.cache.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.spacetraveler.data.cache.database.config.TABLE_TRANSACTION
import com.example.spacetraveler.data.cache.model.CacheTransaction

@Dao
interface TravelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTravel(travel: CacheTransaction)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveListTravel(list: List<CacheTransaction>)

    @Query("SELECT * FROM $TABLE_TRANSACTION")
    suspend fun getListTravel(): List<CacheTransaction>



}