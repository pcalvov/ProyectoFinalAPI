package com.pabcalvid.proyectofinalapi.navigation

sealed class Destinations(val route: String) {
    object MainRoute : Destinations(route = "main_screen")
    object BookRoute : Destinations(route = "book_screen")
}