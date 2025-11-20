package com.example.spacetraveler.ui.navigation

sealed class Routes(val route: String) {
    data object SpaceScreen : Routes(ConstantAppScreenName.SPACE_SCREEN)
    data object SpaceCreateScreen : Routes(ConstantAppScreenName.SPACE_CREATE_SCREEN)

}

object ConstantAppScreenName {
    const val SPACE_SCREEN = "space_screen"
    const val SPACE_CREATE_SCREEN = "space_create_screen"
}