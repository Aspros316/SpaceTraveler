package com.example.spacetraveler.domain

import com.example.spacetraveler.data.repository.model.RemoteTravelResponse
import com.example.spacetraveler.data.source.TravelRepository
import com.example.spacetraveler.utils.RequestUseCase

class GetListTravelUseCase(
    private val remote: TravelRepository
): RequestUseCase<Any, List<RemoteTravelResponse>>() {

    override suspend fun executeOnBackground(params: Any?): List<RemoteTravelResponse> {
        return remote.getListTravel()
    }
}
