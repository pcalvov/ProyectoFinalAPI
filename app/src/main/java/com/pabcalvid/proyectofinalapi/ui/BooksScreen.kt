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
import com.pabcalvid.proyectofinalapi.data.local.Book
import com.pabcalvid.proyectofinalapi.viewModel.ViewModel

@Composable
fun BooksScreen(
    viewModel: ViewModel,
    onBookClick: (Int) -> Unit,
    onRandomBookClick: (Book) -> Unit,
    onBack: () -> Unit
) {
    val books by viewModel.books.collectAsState()
    val randomBook by viewModel.randomBook.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getBooks()
    }

    LaunchedEffect(randomBook) {
        randomBook?.let {
            onRandomBookClick(it)
            viewModel.clearRandomBook()
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Lista de Libros",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.getRandomBook() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Mostrar libro aleatorio")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (books.isEmpty()) {
            Text(
                "Cargando libros...",
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(books) { book ->
                    BookItem(book, onBookClick)
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
fun BookItem(book: Book, onBookClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onBookClick(book.index) },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = book.cover,
                contentDescription = "Portada de ${book.title}",
                modifier = Modifier
                    .size(100.dp)
                    .padding(end = 16.dp)
            )
            Column {
                Text(book.title, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Páginas: ${book.pages}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
