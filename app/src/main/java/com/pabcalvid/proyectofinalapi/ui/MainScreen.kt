package com.pabcalvid.proyectofinalapi.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pabcalvid.proyectofinalapi.R // Importa el archivo R donde están las imágenes
import com.pabcalvid.proyectofinalapi.viewModel.ViewModel

@Composable
fun MainScreen(
    viewModel: ViewModel,
    onBooks: () -> Unit,
    onCharacters: () -> Unit,
    onHouses: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Selecciona una categoría")
        Spacer(Modifier.size(24.dp))

        ImageButton(
            imageRes = R.drawable.libros,
            contentDescription = "Libros",
            onClick = onBooks,
            modifier = Modifier.size(200.dp)
        )

        Spacer(Modifier.size(16.dp))

        ImageButton(
            imageRes = R.drawable.harry_potter,
            contentDescription = "Personajes",
            onClick = onCharacters,
            modifier = Modifier.size(200.dp)
        )

        Spacer(Modifier.size(16.dp))

        ImageButton(
            imageRes = R.drawable.casas,
            contentDescription = "Casas",
            onClick = onHouses,
            modifier = Modifier.size(200.dp)
        )
    }
}

@Composable
fun ImageButton(imageRes: Int, contentDescription: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick)
            .aspectRatio(1f)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}