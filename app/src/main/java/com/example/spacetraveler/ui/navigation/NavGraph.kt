package com.example.spacetraveler.ui.navigation

import androidx.compose.runtime.Composable
import com.example.spacetraveler.presentation.TravelViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.spacetraveler.data.repository.model.RemoteTravelRequest
import com.example.spacetraveler.data.repository.model.RemoteTravelResponse
import com.example.spacetraveler.ui.travel.TravelScreen
import com.example.spacetraveler.ui.travel.create.TravelCreateScreen
import com.example.spacetraveler.ui.travel.detail.TravelDetailScreen


@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
) {
    val viewModel: TravelViewModel = koinViewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.TravelScreen.route
    ) {

        composable(route = Routes.TravelScreen.route) {
            TravelScreen(
                viewModel = viewModel,
                navigateToCreate = {
                    navController.navigate(Routes.TravelCreateScreen.route)
                },
                navToDetail = {response ->
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("travel_data", response)
                    navController.navigate(Routes.TravelNewDetailScreen.route)
                }
            )
        }

        composable(route = Routes.TravelCreateScreen.route) {
            TravelCreateScreen(
                navigateUp = { navController.navigateUp() },
                onCreateTravel = { request ->
                    viewModel.createTravel(request)
                }
            )
        }

        composable(route = Routes.TravelNewDetailScreen.route) {
            val response = navController
                .previousBackStackEntry
                ?.savedStateHandle
                ?.get<Int>("travel_data")
            TravelDetailScreen(
                viewModel = viewModel,
                idTravel = response?: 0,
                navigateUp = { navController.navigateUp() },
                )
        }
    }
}