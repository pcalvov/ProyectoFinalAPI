package com.pabcalvid.proyectofinalapi.ui.util

import com.pabcalvid.proyectofinalapi.data.local.Book

sealed class ScreenState {
    object Loading : ScreenState()
    data class Error(val message: String) : ScreenState()
    data class SuccessBooks(val books: List<Book>) : ScreenState()
    data class SuccessBook(val book: Book) : ScreenState()
}
