package com.pabcalvid.proyectofinalapi.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.pabcalvid.proyectofinalapi.data.local.Character
import com.pabcalvid.proyectofinalapi.viewModel.ViewModel

@Composable
fun FavoritesCharactersScreen(viewModel: ViewModel) {
    val favoriteCharacters by viewModel.favoriteCharacters.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getFavoritesCharacters()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Personajes Favoritos",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (favoriteCharacters.isEmpty()) {
            Text(
                "No tienes personajes favoritos.",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(favoriteCharacters) { character ->
                    FavoriteCharacterItem(character)
                }
            }
        }
    }
}

@Composable
fun FavoriteCharacterItem(character: Character) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = character.image,
                contentDescription = "Imagen de ${character.nickname}",
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 16.dp)
            )
            Column {
                Text(character.nickname, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Casa: ${character.hogwartsHouse}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
