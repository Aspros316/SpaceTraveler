package com.example.spacetraveler.data.source

import com.example.spacetraveler.data.repository.model.RemoteSpaceRequest

interface TravelRepository {

    suspend fun saveTravel(request: RemoteSpaceRequest)

}