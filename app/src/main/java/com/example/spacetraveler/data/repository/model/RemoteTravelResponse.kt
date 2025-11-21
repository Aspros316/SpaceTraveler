package com.example.spacetraveler.data.repository.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteTravelResponse(
    val id: String,
    val name: String,
    val destinyPlanet: String,
    val releaseDate: String,
    val description: String
) : Parcelable
