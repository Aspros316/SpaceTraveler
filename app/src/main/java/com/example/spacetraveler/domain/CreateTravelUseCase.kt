package com.example.spacetraveler.domain

import com.example.spacetraveler.data.repository.model.RemoteSpaceRequest
import com.example.spacetraveler.data.source.TravelRepository
import com.example.spacetraveler.utils.RequestUseCase

class CreateTravelUseCase(
    private val remote: TravelRepository
): RequestUseCase<RemoteSpaceRequest, Any>() {

    override suspend fun executeOnBackground(params: RemoteSpaceRequest?): Any {
        check(params != null)
        return remote.saveTravel(params)
    }
}
