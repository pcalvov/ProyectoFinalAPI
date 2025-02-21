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
import coil3.compose.AsyncImage
import com.pabcalvid.proyectofinalapi.data.local.Character
import com.pabcalvid.proyectofinalapi.viewModel.ViewModel

@Composable
fun CharacterScreen(
    viewModel: ViewModel,
    onCharacterClick: (Int) -> Unit,
    onRandomCharacterClick: (Character) -> Unit,
    onBack: () -> Unit
) {
    val characters by viewModel.characters.collectAsState()
    val randomCharacter by viewModel.randomCharacter.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getCharacters()
    }

    LaunchedEffect(randomCharacter) {
        randomCharacter?.let {
            onRandomCharacterClick(it)
            viewModel.clearRandomCharacter()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Lista de Personajes",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.getRandomCharacter() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Mostrar personaje aleatorio")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (characters.isEmpty()) {
            Text(
                "Cargando personajes...",
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(characters) { character ->
                    CharacterItem(character, onCharacterClick)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}

@Composable
fun CharacterItem(character: Character, onCharacterClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onCharacterClick(character.index) },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = character.image,
                contentDescription = "Imagen de ${character.fullName}",
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 16.dp)
            )
            Column {
                Text(character.fullName, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Casa: ${character.hogwartsHouse}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}


