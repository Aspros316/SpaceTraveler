package com.example.spacetraveler.domain

import com.example.spacetraveler.data.repository.model.RemoteTravelResponse
import com.example.spacetraveler.data.source.TravelRepository
import com.example.spacetraveler.utils.RequestUseCase

class GetTravelerByIdUseCase(
    private val remote: TravelRepository
): RequestUseCase<Int, RemoteTravelResponse>() {

    override suspend fun executeOnBackground(params: Int?): RemoteTravelResponse {
        check(params != null)
        return remote.getTravelerById(params)
    }
}
