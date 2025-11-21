package com.example.spacetraveler.ui.travel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.ui.Alignment
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.spacetraveler.data.repository.model.RemoteTravelResponse
import com.example.spacetraveler.presentation.TravelViewModel
import com.example.spacetraveler.utils.NavTopBar
import com.example.spacetraveler.utils.Result

@Composable
fun TravelScreen(
    viewModel: TravelViewModel,
    navigateToCreate: () -> Unit,
    navToDetail: (Int) -> Unit,
) {

    val uiState = viewModel.spaceListTravelFlow.collectAsStateWithLifecycle()

    TravelState(
        uiState.value,
        navigateToCreate,
        navToDetail
    )
}

@Composable
fun TravelState(
    uiState: Result<List<RemoteTravelResponse>>,
    navigateToCreate: () -> Unit,
    navigateToDetail: (Int) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        topBar = {
            NavTopBar(
                modifier = Modifier,
                title = "Travel Space",
                canNavigateBack = false,
                navigateUp = {},
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
                    BodyTravelScreen(
                        uiState.data,
                        navigateToCreate,
                        navigateToDetail
                    )
                }

                is Result.OnError -> {}
            }
        }
    }
}

@Composable
fun BodyTravelScreen(
    listTravel: List<RemoteTravelResponse>,
    navigateToCreate: () -> Unit,
    navigateToDetail: (Int) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            items(listTravel.count()) { index ->
                val item = listTravel[index]

                Card(
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            navigateToDetail(index + 1)
                        }
                        .padding(start = 24.dp, end = 24.dp, top = 16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                ) {
                    Column(Modifier.padding(8.dp)) {
                        Text(text = item.id.toString())
                        Text(text = item.name)
                        Text(text = item.destinyPlanet)
                        Text(text = item.releaseDate)
                        Text(text = item.description)
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = navigateToCreate,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Agregar")
        }
    }
}