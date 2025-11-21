package com.example.spacetraveler.data.cache

import com.example.spacetraveler.data.cache.database.dao.TravelDao
import com.example.spacetraveler.data.cache.model.CacheTransaction
import com.example.spacetraveler.data.source.TravelCache

class TravelCacheImpl(
    private val travelDao: TravelDao
): TravelCache {
    override suspend fun saveTravel(travel: CacheTransaction) {
        travelDao.saveTravel(travel)
    }

    override suspend fun saveListTravel(list: List<CacheTransaction>) {
        return travelDao.saveListTravel(list)
    }

    override suspend fun getListTravel(): List<CacheTransaction> {
        return travelDao.getListTravel()
    }
}