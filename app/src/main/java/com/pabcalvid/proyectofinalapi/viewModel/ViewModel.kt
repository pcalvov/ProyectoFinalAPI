package com.pabcalvid.proyectofinalapi.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pabcalvid.proyectofinalapi.data.MainRepository
import com.pabcalvid.proyectofinalapi.data.local.Book
import com.pabcalvid.proyectofinalapi.ui.util.ScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ViewModel(private val repository: MainRepository) : ViewModel() {

    private val _book: MutableState<Book> =
        mutableStateOf<Book>(Book(0, 0, "", "", "", "", 0, "", 0))
    var book = _book

    private val _books: MutableStateFlow<List<Book>> = MutableStateFlow(listOf())
    var books = _books.asStateFlow()

    private val _uiState: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Loading)
    val uiState: StateFlow<ScreenState> = _uiState.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, exception ->
        _uiState.value =
            ScreenState.Error("Error, revise su conexión a internet o inténtelo de nuevo más tarde")
    }

    //Metodo que carga todos los libros online
    fun getBooks() {
        viewModelScope.launch(handler) {
            _uiState.value = ScreenState.Loading
            try {
                val books = repository.getBooks()
                _books.value = books ?: emptyList() // Evita que books sea null
                _uiState.value = ScreenState.SuccessBooks(books)
            } catch (e: Exception) {
                _uiState.value = ScreenState.Error("No se pudieron cargar los libros. Revisa tu conexión.")
            }

        }
    }

    //Metodo que carga un libro aleatorio
    fun getRandomBook(){
        viewModelScope.launch(handler) {
            val book = repository.getRandomBook()
            _book.value = book
            _uiState.value = ScreenState.SuccessBook(book) // ✅ Usamos SuccessBook para un solo libro
        }
    }
}