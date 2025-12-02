package com.example.spacetraveler.data.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.spacetraveler.data.cache.database.config.TABLE_TRANSACTION


@Entity(tableName = TABLE_TRANSACTION)
data class CacheTransaction (
    @PrimaryKey
    @ColumnInfo(name = "travel_id")
    val travelId: Int,

    @ColumnInfo(name = "travel_name")
    val name: String,

    @ColumnInfo(name = "destiny_planet")
    val destinyPlanet: String,

    @ColumnInfo(name = "release_date")
    val releaseDate: String,

    @ColumnInfo(name = "description")
    val description: String,
)