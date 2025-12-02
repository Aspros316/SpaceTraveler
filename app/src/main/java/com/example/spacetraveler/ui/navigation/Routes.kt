package com.example.spacetraveler.ui.navigation

import kotlinx.serialization.Serializable


@Serializable
object TravelScreen

@Serializable
object TravelCreateScreen

@Serializable
data class TravelDetailScreen(val id:Int)

