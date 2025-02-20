package com.pabcalvid.proyectofinalapi.navigation

sealed class Destinations(val route: String) {
    object MainRoute : Destinations("main")
    object BookRoute : Destinations("books")
    object BookDetailsRoute : Destinations("bookDetails/{bookIndex}") {
        fun createRoute(bookIndex: Int) = "bookDetails/$bookIndex"
    }
}
