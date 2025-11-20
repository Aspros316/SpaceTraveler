package com.example.spacetraveler.ui.travel.create

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Boy
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.spacetraveler.utils.ContractDataInput
import com.example.spacetraveler.utils.NavTopBar

@Composable
fun SpaceCreateScreen(
    navigateUp: () -> Unit,
    ) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        topBar = {
            NavTopBar(
                modifier = Modifier,
                title = "Crear Viaje",
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
            BodyTravelScreen()
        }
    }
}

@Composable
fun BodyTravelScreen() {
    val name = rememberSaveable { mutableStateOf("") }
    val destinyPlanet = rememberSaveable { mutableStateOf("") }
    val releaseDate = rememberSaveable { mutableStateOf("") }
    val description = rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.size(5.dp))


        ContractDataInput(
            textValue = "Nombre deel viaje",
            placeHolder = "Jupiter",
            value = name.value,
            onTextChanged = { input ->
                val maxNameLength = 50
                val filtered = input
                    .filter { it.isLetter() || it.isWhitespace() }
                    .take(maxNameLength)
               name.value = filtered
            },
            leadingIcon = rememberVectorPainter(Icons.Filled.Boy),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )
    }
}
