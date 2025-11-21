package com.example.spacetraveler.ui.travel.create

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Boy
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.spacetraveler.data.repository.model.RemoteTravelRequest
import com.example.spacetraveler.utils.ContractDataInput
import com.example.spacetraveler.utils.NavTopBar
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun TravelCreateScreen(
    navigateUp: () -> Unit,
    onCreateTravel: (RemoteTravelRequest) -> Unit,
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
            BodyCreateTravelScreen(onCreateTravel)
        }
    }
}

@SuppressLint("NewApi")
@Composable
fun BodyCreateTravelScreen(
    onCreateTravel: (RemoteTravelRequest) -> Unit,
) {
    val name = rememberSaveable { mutableStateOf("") }
    val destinyPlanet = rememberSaveable { mutableStateOf("") }
    val description = rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.size(5.dp))


        ContractDataInput(
            textValue = "Nombre del viaje",
            placeHolder = "Investigación",
            value = name.value,
            onTextChanged = { input ->
                val maxNameLength = 30
                val filtered = input
                    .filter { it.isLetter() || it.isWhitespace() }
                    .take(maxNameLength)
                name.value = filtered
            },
            leadingIcon = rememberVectorPainter(Icons.Filled.Boy),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )

        Spacer(modifier = Modifier.size(8.dp))

        ContractDataInput(
            textValue = "Planeta de destino",
            placeHolder = "Jupiter",
            value = destinyPlanet.value,
            onTextChanged = { input ->
                val maxNameLength = 30
                val filtered = input
                    .take(maxNameLength)
                destinyPlanet.value = filtered
            },
            leadingIcon = rememberVectorPainter(Icons.Filled.Boy),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )

        Spacer(modifier = Modifier.size(8.dp))

        ContractDataInput(
            textValue = "Descripcion del viaje",
            placeHolder = "Breve descripcion del viaje",
            value = description.value,
            onTextChanged = { input ->
                val maxNameLength = 50
                val filtered = input
                    .take(maxNameLength)
                description.value = filtered
            },
            leadingIcon = rememberVectorPainter(Icons.Filled.Boy),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        )

        Spacer(modifier = Modifier.size(24.dp))

        Button(
            onClick = {
                onCreateTravel(
                    RemoteTravelRequest(
                        name = name.value,
                        destinyPlanet = destinyPlanet.value,
                        releaseDate = LocalDate.now()
                            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                        description = description.value
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White,
                disabledContainerColor = Color.LightGray,
                disabledContentColor = Color.White
            )
        ) {
            Spacer(modifier = Modifier.width(6.dp))
            Text("Crear Viaje")
        }
    }
}
