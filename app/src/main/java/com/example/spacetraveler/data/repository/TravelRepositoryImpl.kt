package com.example.spacetraveler.data.repository

import com.example.spacetraveler.data.repository.model.RemoteSpaceRequest
import com.example.spacetraveler.data.repository.retrofit.SpaceWebService
import com.example.spacetraveler.data.source.TravelRepository

class TravelRepositoryImpl(
    private val webService: SpaceWebService,
): TravelRepository {

    override suspend fun saveTravel(request: RemoteSpaceRequest) {
        webService.createTraveler(request)

    }
}