package com.pabcalvid.proyectofinalapi.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.pabcalvid.proyectofinalapi.viewModel.ViewModel

@Composable
fun CharacterDetailsScreen(viewModel: ViewModel) {

    val character by viewModel.character.collectAsState()
    val favoriteCharacters by viewModel.favoriteCharacters.collectAsState()

    Log.d("CharacterDetailsScreen", "Personaje recibido: $character")

    if (character != null) {
        val isFavorite = favoriteCharacters.any { it.nickname == character!!.nickname }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                character!!.fullName,
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))

            AsyncImage(
                model = character!!.image,
                contentDescription = "Imagen de ${character!!.fullName}",
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text("Apodo: ${character!!.nickname}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Casa de Hogwarts: ${character!!.hogwartsHouse}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Interpretado por: ${character!!.interpretedBy}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Hijos: ${character!!.children.joinToString(", ")}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Fecha de Nacimiento: ${character!!.birthdate}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))

            // Botón de favorito
            IconButton(
                onClick = { viewModel.toggleFavoriteCharacter(character!!) }
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
