package com.example.spacetraveler.data.source

import com.example.spacetraveler.data.repository.model.RemoteTravelRequest
import com.example.spacetraveler.data.repository.model.RemoteTravelResponse

interface TravelRepository {
    suspend fun getListTravel(): List<RemoteTravelResponse>
    suspend fun saveTravel(request: RemoteTravelRequest)
    suspend fun getTravelerById(id: Int): RemoteTravelResponse
}