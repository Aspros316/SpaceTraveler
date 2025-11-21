package com.example.spacetraveler.domain

import com.example.spacetraveler.data.repository.model.RemoteTravelRequest
import com.example.spacetraveler.data.source.TravelRepository
import com.example.spacetraveler.utils.RequestUseCase

class CreateTravelUseCase(
    private val remote: TravelRepository
): RequestUseCase<RemoteTravelRequest, Any>() {

    override suspend fun executeOnBackground(params: RemoteTravelRequest?): Any {
        check(params != null)
        return remote.saveTravel(params)
    }
}
