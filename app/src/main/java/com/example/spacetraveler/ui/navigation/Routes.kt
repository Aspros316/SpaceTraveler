package com.example.spacetraveler.ui.navigation

sealed class Routes(val route: String) {
    data object TravelScreen : Routes(ConstantAppScreenName.TRAVEL_SCREEN)
    data object TravelCreateScreen : Routes(ConstantAppScreenName.TRAVEL_CREATE_SCREEN)
    data object TravelNewDetailScreen : Routes(ConstantAppScreenName.TRAVEL_NEW_DETAIL_SCREEN)

    data object TravelDetailScreen : Routes("travel_detail_screen/{id}") {
        fun routeWithId(id: Int): String = "travel_detail_screen/$id"
    }
}

object ConstantAppScreenName {
    const val TRAVEL_SCREEN = "travel_screen"
    const val TRAVEL_CREATE_SCREEN = "travel_create_screen"
    const val TRAVEL_NEW_DETAIL_SCREEN = "travel_detail_screen"

}

