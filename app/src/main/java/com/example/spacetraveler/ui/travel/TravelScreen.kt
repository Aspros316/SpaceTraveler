package com.example.spacetraveler.ui.travel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.ui.Alignment
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
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
        floatingActionButtonPosition = FabPosition.Start,
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.size(82.dp),
                onClick = navigateToCreate,

            ) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar")
            }
        }
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
                        navigateToDetail,
                        innerPadding
                    )
                }

                is Result.OnError -> {
                    ErrorDialog(uiState.throwable)
                }
            }
        }
    }
}

@Composable
fun BodyTravelScreen(
    listTravel: List<RemoteTravelResponse>,
    navigateToCreate: () -> Unit,
    navigateToDetail: (Int) -> Unit,
    innerPadding: PaddingValues
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(
                top = 16.dp,
                bottom = innerPadding.calculateBottomPadding(),
                start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                end = innerPadding.calculateEndPadding(LocalLayoutDirection.current)
            )
        ) {
            items(listTravel) { listTravel ->
                Card(
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            navigateToDetail(listTravel.id)
                        }
                        .padding(start = 24.dp, end = 24.dp, top = 16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                ) {
                    Column(Modifier.padding(8.dp)) {
                        Text(text = listTravel.id.toString())
                        Text(text = listTravel.name)
                        Text(text = listTravel.destinyPlanet)
                        Text(text = listTravel.releaseDate)
                        Text(text = listTravel.description)
                    }
                }
            }
        }
    }
}


@Composable
fun ErrorDialog(throwable: Throwable) {
    var isDialogOpen by remember { mutableStateOf(true) }
    if (isDialogOpen) {
        AlertDialog(
            onDismissRequest = {
                isDialogOpen = false
            },
            title = {
                Text("Ha ocurrido un error")
            },

            text = {
                Text(throwable.message.toString())
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        isDialogOpen = false
                    }
                ) {
                    Text("Aceptar")
                }
            }
        )
    }
}