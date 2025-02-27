package com.pabcalvid.proyectofinalapi.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pabcalvid.proyectofinalapi.data.MainRepository
import com.pabcalvid.proyectofinalapi.data.local.Book
import com.pabcalvid.proyectofinalapi.data.local.Character
import com.pabcalvid.proyectofinalapi.data.local.House
import com.pabcalvid.proyectofinalapi.ui.util.ScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
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

    private val _favoriteBooks: MutableStateFlow<List<Book>> = MutableStateFlow(emptyList())
    val favoriteBooks: StateFlow<List<Book>> = _favoriteBooks.asStateFlow()

    private val _book = MutableStateFlow<Book?>(null)
    val book: StateFlow<Book?> = _book.asStateFlow()

    // Personajes
    private val _characters: MutableStateFlow<List<Character>> = MutableStateFlow(emptyList())
    val characters: StateFlow<List<Character>> = _characters.asStateFlow()

    private val _randomCharacter: MutableStateFlow<Character?> = MutableStateFlow(null)
    val randomCharacter: StateFlow<Character?> = _randomCharacter.asStateFlow()

    private val _favoriteCharacters: MutableStateFlow<List<Character>> = MutableStateFlow(emptyList())
    val favoriteCharacters: StateFlow<List<Character>> = _favoriteCharacters.asStateFlow()

    private val _character = MutableStateFlow<Character?>(null)
    val character: StateFlow<Character?> = _character.asStateFlow()

    // Casas
    private val _houses: MutableStateFlow<List<House>> = MutableStateFlow(emptyList())
    val houses: StateFlow<List<House>> = _houses.asStateFlow()

    private val _randomHouse: MutableStateFlow<House?> = MutableStateFlow(null)
    val randomHouse: StateFlow<House?> = _randomHouse.asStateFlow()

    private val _house = MutableStateFlow<House?>(null)
    val house: StateFlow<House?> = _house.asStateFlow()

    private val _favoriteHouses: MutableStateFlow<List<House>> = MutableStateFlow(emptyList())
    val favoriteHouses: StateFlow<List<House>> = _favoriteHouses.asStateFlow()

    // Estado de la UI
    private val _uiState: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Loading)
    val uiState: StateFlow<ScreenState> = _uiState.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, _ ->
        _uiState.value =
            ScreenState.Error("Error, revise su conexión a internet o inténtelo de nuevo más tarde")
    }

    init {
        getFavoritesBooks()
        getFavoritesCharacters()
        getFavoritesHouses()
    }

    fun setSelectedBook(selectedBook: Book) {
        _book.value = selectedBook
        Log.d("ViewModel", "Libro seleccionado: ${selectedBook.title}")
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

    fun getFavoritesBooks() {
        viewModelScope.launch {
            repository.getAllBooks().collect { books ->
                _favoriteBooks.value = books.filter { it.isFavorite }
            }
        }
    }

    fun toggleFavoriteBook(book: Book) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.toggleFavoriteBook(book)
        }
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
    fun getCharacterByNickname(nickname: String): Character? {
        return characters.value.find { it.nickname == nickname }
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

    fun getFavoritesCharacters() {
        viewModelScope.launch {
            repository.getAllCharacters().collect { characters ->
                _favoriteCharacters.value = characters.filter { it.isFavorite }
            }
        }
    }

    fun toggleFavoriteCharacter(character: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.toggleFavoriteCharacter(character)
        }
    }

    fun setSelectedCharacter(selectedCharacter: Character) {
        _character.value = selectedCharacter
        Log.d("ViewModel", "Personaje seleccionado: ${selectedCharacter.nickname}")
    }

    // Obtener lista de casas
    fun getHouses() {
        viewModelScope.launch(handler) {
            _uiState.value = ScreenState.Loading
            try {
                val houseList = repository.getHouses()
                _houses.value = houseList
                _uiState.value = ScreenState.SuccessHouses(houseList)
            } catch (e: Exception) {
                _uiState.value = ScreenState.Error("No se pudieron cargar las casas. Revisa tu conexión.")
            }
        }
    }

    // Obtener personaje por índice
    fun getHouseByHouse(house: String): House? {
        return houses.value.find { it.house == house }
    }

    // Obtener personaje aleatorio
    fun getRandomHouse() {
        viewModelScope.launch(handler) {
            try {
                val house = repository.getRandomHouse()
                _randomHouse.value = house
            } catch (e: Exception) {
                _uiState.value = ScreenState.Error("No se pudo obtener una casa aleatoria.")
            }
        }
    }

    fun clearRandomHouse() {
        _randomHouse.value = null
    }

    fun getFavoritesHouses() {
        viewModelScope.launch {
            repository.getAllHouses().collect { houses ->
                _favoriteHouses.value = houses.filter { it.isFavorite }
            }
        }
    }

    fun toggleFavoriteHouse(house: House) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.toggleFavoriteHouse(house)
        }
    }

    fun setSelectedHouse(selectedHouse: House) {
        _house.value = selectedHouse
        Log.d("ViewModel", "Casa seleccionada: ${selectedHouse.house}")
    }
}