package com.example.spacetraveler.data.repository.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteTravelRequest(
    @SerialName("id") val id: Int = 0,
    @SerialName("name") val name: String,
    @SerialName("destiny_planet") val destinyPlanet: String,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("description") val description: String,
)

