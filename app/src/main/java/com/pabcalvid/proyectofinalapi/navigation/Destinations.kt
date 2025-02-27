package com.pabcalvid.proyectofinalapi.navigation

sealed class Destinations(val route: String) {
    object MainRoute : Destinations("main")
    object BookRoute : Destinations("books")
    object BookDetailsRoute : Destinations("bookDetails/{bookIndex}") {
        fun createRoute(bookIndex: Int) = "bookDetails/$bookIndex"
    }

    object CharacterRoute : Destinations("characters")
    object CharacterDetailsRoute: Destinations("character_details/{nickname}") {
        fun createRoute(nickname: String) = "character_details/$nickname"
    }

    object HouseRoute : Destinations("houses")
    object HouseDetailsRoute : Destinations("houseDetails/{house}") {
        fun createRoute(house: String) = "houseDetails/$house"
    }

    // 📌 Nueva ruta para favoritos
    object FavoritesRoute : Destinations("favorites")

    object FavoritesCharactersRoute : Destinations("favorites_characters")

    object FavoritesHousesRoute : Destinations("favorites_houses")

    object FavoritesSelectionRoute : Destinations("favorites_selection")


}
