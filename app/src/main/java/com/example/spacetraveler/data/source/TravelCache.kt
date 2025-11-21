package com.example.spacetraveler.data.source

import com.example.spacetraveler.data.cache.model.CacheTransaction

interface TravelCache {
    suspend fun saveTravel(travel: CacheTransaction)
    suspend fun saveListTravel(list: List<CacheTransaction>)
    suspend fun getListTravel(): List<CacheTransaction>
}