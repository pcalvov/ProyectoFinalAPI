package com.pabcalvid.proyectofinalapi.ui.util

import com.pabcalvid.proyectofinalapi.data.local.Book

sealed class ScreenState {

    object Loading : ScreenState()

    data class Error(val message: String) : ScreenState()
    data class Success(val book: Book) : ScreenState()
}