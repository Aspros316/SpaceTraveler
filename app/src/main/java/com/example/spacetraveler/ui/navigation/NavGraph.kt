package com.example.spacetraveler.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.spacetraveler.presentation.TravelViewModel
import com.example.spacetraveler.ui.travel.TravelScreen
import com.example.spacetraveler.ui.travel.create.TravelCreateScreen
import com.example.spacetraveler.ui.travel.detail.TravelDetailScreen
import org.koin.androidx.compose.koinViewModel


@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
) {
    val viewModel: TravelViewModel = koinViewModel()

    NavHost(
        navController = navController,
        startDestination = TravelScreen
    ) {

        composable<TravelScreen> {
            TravelScreen(
                viewModel = viewModel,
                navigateToCreate = {
                    navController.navigate(TravelCreateScreen)
                },
                navToDetail = { id ->
                    navController.navigate(TravelDetailScreen(id = id))
                }
            )
        }

        composable<TravelCreateScreen> {
            TravelCreateScreen(
                navigateUp = { navController.navigateUp() },
                onCreateTravel = { request ->
                    viewModel.createTravel(request)
                }
            )
        }

        composable<TravelDetailScreen> { navBackStackEntry ->
            val detail: TravelDetailScreen = navBackStackEntry.toRoute<TravelDetailScreen>()

            TravelDetailScreen(
                viewModel = viewModel,
                idTravel = detail.id,
                navigateUp = { navController.navigateUp() },
            )
        }
    }
}