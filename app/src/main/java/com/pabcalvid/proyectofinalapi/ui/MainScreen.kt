package com.pabcalvid.proyectofinalapi.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.pabcalvid.proyectofinalapi.data.local.Book
import com.pabcalvid.proyectofinalapi.viewModel.ViewModel

@Composable
fun MainScreen(viewModel: ViewModel, onList: () -> Unit) {
    val book by viewModel.book

    // Si no hay un libro cargado, cargamos uno aleatorio al inicio
    if (book.id == null) {
        viewModel.getRandomBook()
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(Modifier.size(24.dp))
        Text(text = book.title)
        Spacer(Modifier.size(16.dp))
        CoilImage(book)
        Spacer(Modifier.size(24.dp))
        Button(onClick = { viewModel.getRandomBook() }) {
            Text(text = "Cargar libro aleatorio")
        }
        Spacer(Modifier.size(16.dp))
        Button(onClick = onList) {
            Text(text = "Ver lista de libros")
        }
    }
}

@Composable
fun CoilImage(book: Book) {
    val bookModel = ImageRequest.Builder(LocalContext.current)
        .data(book.cover)
        .crossfade(true)
        .build()

    AsyncImage(
        model = bookModel,
        contentDescription = book.title,
        modifier = Modifier.size(360.dp),
        contentScale = ContentScale.Fit
    )
}
