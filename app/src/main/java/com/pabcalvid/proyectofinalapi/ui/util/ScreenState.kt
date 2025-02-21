package com.pabcalvid.proyectofinalapi.ui.util

import com.pabcalvid.proyectofinalapi.data.local.Book
import com.pabcalvid.proyectofinalapi.data.local.Character

sealed class ScreenState {
    object Loading : ScreenState()
    data class Error(val message: String) : ScreenState()

    // Estados para libros
    data class SuccessBooks(val books: List<Book>) : ScreenState()
    data class SuccessBook(val book: Book) : ScreenState()

    // Estados para personajes
    data class SuccessCharacters(val characters: List<Character>) : ScreenState()
    data class SuccessCharacter(val character: Character) : ScreenState()
}
