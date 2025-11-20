package com.example.spacetraveler.ui.navigation

import androidx.compose.runtime.Composable
import com.example.spacetraveler.presentation.SpaceViewModel
import com.example.spacetraveler.ui.travel.SpaceScreen
import org.koin.androidx.compose.koinViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.spacetraveler.ui.travel.create.SpaceCreateScreen


@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
) {

    NavHost(
        navController = navController,
        startDestination = Routes.SpaceScreen.route
    ) {

        composable(route = Routes.SpaceScreen.route) {
            val viewModel: SpaceViewModel = koinViewModel()
            SpaceScreen(
                navigateToCreate = {
                    navController.navigate(Routes.SpaceCreateScreen.route)
                }
            )
        }

        composable(route = Routes.SpaceCreateScreen.route) {
            val viewModel: SpaceViewModel = koinViewModel()
            SpaceCreateScreen(
                navigateUp = { navController.navigateUp() },
            )
        }

    }
}