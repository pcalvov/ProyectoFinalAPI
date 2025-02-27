package com.pabcalvid.proyectofinalapi.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pabcalvid.proyectofinalapi.data.local.House
import com.pabcalvid.proyectofinalapi.viewModel.ViewModel

@Composable
fun FavoriteHousesScreen(viewModel: ViewModel) {
    val favoriteHouses by viewModel.favoriteHouses.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getFavoritesHouses()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Casas Favoritas",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (favoriteHouses.isEmpty()) {
            Text(
                "No tienes personajes favoritos.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(favoriteHouses) { house ->
                    FavoriteHouseItem(house)
                }
            }
        }
    }
}

@Composable
fun FavoriteHouseItem(house: House) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
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