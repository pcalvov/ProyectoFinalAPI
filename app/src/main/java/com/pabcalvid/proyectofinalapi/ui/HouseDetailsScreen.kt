package com.pabcalvid.proyectofinalapi.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pabcalvid.proyectofinalapi.viewModel.ViewModel

@Composable
fun HouseDetailsScreen(viewModel: ViewModel) {

    val house by viewModel.house.collectAsState()
    val favoriteHouses by viewModel.favoriteHouses.collectAsState()

    if (house != null) {
        val isFavorite = favoriteHouses.any { it.house == house!!.house }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(house!!.house, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = house!!.emoji,
                fontSize = 48.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Fundador: ${house!!.founder}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Colores: ${house!!.colors.joinToString(", ")}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Animal Representativo: ${house!!.animal}", style = MaterialTheme.typography.bodyLarge)

            IconButton(
                onClick = { viewModel.toggleFavoriteHouse(house!!) }
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorito",
                    tint = if (isFavorite) Color.Red else Color.Gray
                )
            }
        }
    }
}