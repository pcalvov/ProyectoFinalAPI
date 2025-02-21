package com.pabcalvid.proyectofinalapi.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pabcalvid.proyectofinalapi.data.MainRepository
import com.pabcalvid.proyectofinalapi.data.local.Book
import com.pabcalvid.proyectofinalapi.data.local.Character
import com.pabcalvid.proyectofinalapi.ui.util.ScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ViewModel(private val repository: MainRepository) : ViewModel() {

    // Libros
    private val _books: MutableStateFlow<List<Book>> = MutableStateFlow(emptyList())
    val books: StateFlow<List<Book>> = _books.asStateFlow()

    private val _randomBook: MutableStateFlow<Book?> = MutableStateFlow(null)
    val randomBook: StateFlow<Book?> = _randomBook.asStateFlow()

    // Personajes
    private val _characters: MutableStateFlow<List<Character>> = MutableStateFlow(emptyList())
    val characters: StateFlow<List<Character>> = _characters.asStateFlow()

    private val _randomCharacter: MutableStateFlow<Character?> = MutableStateFlow(null)
    val randomCharacter: StateFlow<Character?> = _randomCharacter.asStateFlow()

    // Estado de la UI
    private val _uiState: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Loading)
    val uiState: StateFlow<ScreenState> = _uiState.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, _ ->
        _uiState.value =
            ScreenState.Error("Error, revise su conexión a internet o inténtelo de nuevo más tarde")
    }

    // Obtener lista de libros
    fun getBooks() {
        viewModelScope.launch(handler) {
            _uiState.value = ScreenState.Loading
            try {
                val booksList = repository.getBooks()
                _books.value = booksList
                _uiState.value = ScreenState.SuccessBooks(booksList)
            } catch (e: Exception) {
                _uiState.value = ScreenState.Error("No se pudieron cargar los libros. Revisa tu conexión.")
            }
        }
    }

    // Obtener libro por índice
    fun getBookByIndex(index: Int): Book? {
        return books.value.find { it.index == index }
    }

    // Obtener libro aleatorio
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

    // Obtener lista de personajes
    fun getCharacters() {
        viewModelScope.launch(handler) {
            _uiState.value = ScreenState.Loading
            try {
                val characterList = repository.getCharacters()
                _characters.value = characterList
                _uiState.value = ScreenState.SuccessCharacters(characterList)
            } catch (e: Exception) {
                _uiState.value = ScreenState.Error("No se pudieron cargar los personajes. Revisa tu conexión.")
            }
        }
    }

    // Obtener personaje por índice
    fun getCharacterByIndex(index: Int): Character? {
        return characters.value.find { it.index == index }
    }

    // Obtener personaje aleatorio
    fun getRandomCharacter() {
        viewModelScope.launch(handler) {
            try {
                val character = repository.getRandomCharacter()
                _randomCharacter.value = character
            } catch (e: Exception) {
                _uiState.value = ScreenState.Error("No se pudo obtener un personaje aleatorio.")
            }
        }
    }

    fun clearRandomCharacter() {
        _randomCharacter.value = null
    }
}
