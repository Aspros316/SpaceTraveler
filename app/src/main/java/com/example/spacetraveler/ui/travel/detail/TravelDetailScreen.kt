package com.example.spacetraveler.ui.travel.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.spacetraveler.data.repository.model.RemoteTravelResponse
import com.example.spacetraveler.presentation.TravelViewModel
import com.example.spacetraveler.ui.travel.TravelState
import com.example.spacetraveler.utils.NavTopBar
import com.example.spacetraveler.utils.Result

@Composable
fun TravelDetailScreen(
    viewModel: TravelViewModel,
    idTravel: Int,
    navigateUp: () -> Unit,
) {

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getTravelerById(idTravel)
    })

    val uiState = viewModel.getTravelerById.collectAsStateWithLifecycle()
    TravelState(
        uiState.value,
        navigateUp
    )
}

@Composable
fun TravelState(
    uiState: Result<RemoteTravelResponse>,
    navigateUp: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        topBar = {
            NavTopBar(
                modifier = Modifier,
                title = "Travel Detail",
                canNavigateBack = true,
                navigateUp = navigateUp,
            )

        },
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (uiState) {
                is Result.OnLoading -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is Result.OnSuccess -> {
                    BodyTravelDetailScreen(
                        uiState.data,
                    )
                }

                is Result.OnError -> {}
            }
        }
    }
}

@Composable
fun BodyTravelDetailScreen(data: RemoteTravelResponse) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
        ) {
            Column(Modifier.padding(8.dp)) {
                Text(text = data.id.toString())
                Text(text = data.name)
                Text(text = data.destinyPlanet)
                Text(text = data.releaseDate)
                Text(text = data.description)
            }
        }
    }
}