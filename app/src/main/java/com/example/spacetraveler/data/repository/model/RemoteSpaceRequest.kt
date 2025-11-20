package com.example.spacetraveler.data.repository.model

import com.google.gson.annotations.SerializedName

data class RemoteSpaceRequest(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("destiny_planet") val destinyPlanet: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("description") val description: String,
)

