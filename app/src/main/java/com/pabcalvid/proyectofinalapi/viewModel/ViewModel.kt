package com.pabcalvid.proyectofinalapi.viewModel

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

    private val _books: MutableStateFlow<List<Book>> = MutableStateFlow(listOf())
    var books = _books.asStateFlow()

    private val _randomBook: MutableStateFlow<Book?> = MutableStateFlow(null)
    val randomBook: StateFlow<Book?> = _randomBook.asStateFlow()

    private val _uiState: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Loading)
    val uiState: StateFlow<ScreenState> = _uiState.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, _ ->
        _uiState.value =
            ScreenState.Error("Error, revise su conexión a internet o inténtelo de nuevo más tarde")
    }

    fun getBooks() {
        viewModelScope.launch(handler) {
            _uiState.value = ScreenState.Loading
            try {
                val books = repository.getBooks()
                _books.value = books
                _uiState.value = ScreenState.SuccessBooks(books)
            } catch (e: Exception) {
                _uiState.value = ScreenState.Error("No se pudieron cargar los libros. Revisa tu conexión.")
            }
        }
    }

    fun getBookByIndex(index: Int): Book? {
        return books.value.find { it.index == index }
    }

    fun getRandomBook() {
        viewModelScope.launch(handler) {
            try {
                val book = repository.getRandomBook()
                _randomBook.value = book
            } catch (e: Exception) {
                _uiState.value = ScreenState.Error("No se pudo obtener un libro aleatorio.")
            }
        }
    }

    fun clearRandomBook() {
        _randomBook.value = null
    }
}
