package com.example.spacetraveler.data.repository

import com.example.spacetraveler.data.mapper.toCache
import com.example.spacetraveler.data.mapper.toCacheList
import com.example.spacetraveler.data.mapper.toRemoteList
import com.example.spacetraveler.data.mapper.toRemoteRequest
import com.example.spacetraveler.data.repository.model.RemoteTravelRequest
import com.example.spacetraveler.data.repository.model.RemoteTravelResponse
import com.example.spacetraveler.data.repository.retrofit.SpaceWebService
import com.example.spacetraveler.data.source.TravelCache
import com.example.spacetraveler.data.source.TravelRepository
import com.example.spacetraveler.utils.NetworkHandler

class TravelRepositoryImpl(
    private val webService: SpaceWebService,
    private val cache: TravelCache,
    private val networkHandler: NetworkHandler
): TravelRepository {

    override suspend fun getListTravel(): List<RemoteTravelResponse> {
        return if (networkHandler.isOnline()) {
            val remoteList = webService.getListTraveler()
            cache.saveListTravel(remoteList.toCacheList())
            remoteList
        } else {
            val cachedList = cache.getListTravel()
            cachedList.toRemoteList()
        }
    }

    override suspend fun saveTravel(request: RemoteTravelRequest) {
        val cacheEntity = request.toCache()
        cache.saveTravel(cacheEntity)
        webService.createTravel(cacheEntity.toRemoteRequest())
    }

    override suspend fun getTravelerById(id: Int): RemoteTravelResponse {
        return webService.getTravelerById(id)
    }
}