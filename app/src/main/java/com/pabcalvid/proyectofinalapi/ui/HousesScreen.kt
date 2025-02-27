package com.pabcalvid.proyectofinalapi.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pabcalvid.proyectofinalapi.data.local.House
import com.pabcalvid.proyectofinalapi.viewModel.ViewModel

@Composable
fun HousesScreen(
    viewModel: ViewModel,
    onHouseClick: (String) -> Unit,
    onRandomHouseClick: (House) -> Unit
) {
    val houses by viewModel.houses.collectAsState()
    val randomHouse by viewModel.randomHouse.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getHouses()
    }

    LaunchedEffect(randomHouse) {
        randomHouse?.let { house ->
            viewModel.setSelectedHouse(house)
            onRandomHouseClick(house)
            viewModel.clearRandomHouse()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Lista de Casas",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.getRandomHouse() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Mostrar casa aleatoria")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (houses.isEmpty()) {
            Text(
                "Cargando casas...",
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(houses) { house ->
                    HouseItem(house, onHouseClick = onHouseClick, viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun HouseItem(house: House, onHouseClick: (String) -> Unit, viewModel: ViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                viewModel.setSelectedHouse(house)
                onHouseClick(house.house)
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Text(
                text = house.emoji,
                fontSize = 32.sp,
                modifier = Modifier.padding(end = 16.dp)
            )
            Column {
                Text(house.house, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Fundador: ${house.founder}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
