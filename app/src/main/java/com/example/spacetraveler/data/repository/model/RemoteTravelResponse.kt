package com.example.spacetraveler.data.repository.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteTravelResponse(
    val id: Int,
    val name: String,
    val destinyPlanet: String,
    val releaseDate: String,
    val description: String
)
