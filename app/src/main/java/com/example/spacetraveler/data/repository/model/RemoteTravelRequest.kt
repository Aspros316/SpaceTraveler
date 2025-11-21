package com.example.spacetraveler.data.repository.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class RemoteTravelRequest(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String,
    @SerializedName("destiny_planet") val destinyPlanet: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("description") val description: String,
)

