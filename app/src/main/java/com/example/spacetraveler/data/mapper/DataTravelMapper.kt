package com.example.spacetraveler.data.mapper

import com.example.spacetraveler.data.cache.model.CacheTransaction
import com.example.spacetraveler.data.repository.model.RemoteTravelRequest
import com.example.spacetraveler.data.repository.model.RemoteTravelResponse

fun RemoteTravelResponse.toCache(): CacheTransaction {
    return CacheTransaction(
        travelId = this.id,
        name = this.name,
        destinyPlanet = this.destinyPlanet,
        releaseDate = this.releaseDate,
        description = this.description
    )
}

fun List<RemoteTravelResponse>.toCacheList(): List<CacheTransaction> =
    this.map { it.toCache() }

fun RemoteTravelRequest.toCache(): CacheTransaction {
    return CacheTransaction(
        travelId = this.id,
        name = this.name,
        destinyPlanet = this.destinyPlanet,
        releaseDate = this.releaseDate,
        description = this.description
    )
}

    fun CacheTransaction.toRemoteRequest(): RemoteTravelRequest {
        return RemoteTravelRequest(
            id = this.travelId.toInt(),
            name = this.name,
            destinyPlanet = this.destinyPlanet,
            releaseDate = this.releaseDate,
            description = this.description
        )
}

fun CacheTransaction.toRemoteResponse(): RemoteTravelResponse {
    return RemoteTravelResponse(
        id = this.travelId,
        name = this.name,
        destinyPlanet = this.destinyPlanet,
        releaseDate = this.releaseDate,
        description = this.description
    )
}

fun List<CacheTransaction>.toRemoteList(): List<RemoteTravelResponse> =
    this.map { it.toRemoteResponse() }
