package com.pabcalvid.proyectofinalapi.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.pabcalvid.proyectofinalapi.data.local.House

@Composable
fun HouseDetailsScreen(house: House) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(house.house, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = house.emoji,
            fontSize = 48.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Fundador: ${house.founder}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Colores: ${house.colors.joinToString(", ")}", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Animal Representativo: ${house.animal}", style = MaterialTheme.typography.bodyLarge)
    }
}