package com.pabcalvid.proyectofinalapi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pabcalvid.proyectofinalapi.ui.*
import com.pabcalvid.proyectofinalapi.viewModel.ViewModel

@Composable
fun Navigation(navController: NavHostController, mainViewModel: ViewModel) {
    NavHost(
        navController = navController,
        startDestination = Destinations.MainRoute.route,
    ) {
        // Pantalla principal
        composable(route = Destinations.MainRoute.route) {
            MainScreen(
                viewModel = mainViewModel,
                onBooks = { navController.navigate(Destinations.BookRoute.route) },
                onCharacters = { navController.navigate(Destinations.CharacterRoute.route) },
                onHouses = { navController.navigate(Destinations.HouseRoute.route) } // Ahora navega a HousesScreen
            )
        }

        // Pantalla de lista de libros
        composable(route = Destinations.BookRoute.route) {
            BooksScreen(
                viewModel = mainViewModel,
                onBookClick = { bookIndex ->
                    if (bookIndex != -1) {
                        navController.navigate(Destinations.BookDetailsRoute.createRoute(bookIndex))
                    }
                },
                onRandomBookClick = { book ->
                    navController.navigate(Destinations.BookDetailsRoute.createRoute(book.index))
                },
                onBack = { navController.popBackStack() }
            )
        }

        // Pantalla de detalles de un libro
        composable(
            route = Destinations.BookDetailsRoute.route,
            arguments = listOf(navArgument("bookIndex") { defaultValue = -1 })
        ) { backStackEntry ->
            val bookIndex = backStackEntry.arguments?.getInt("bookIndex") ?: -1
            val book = mainViewModel.getBookByIndex(bookIndex)

            if (book != null) {
                BookDetailsScreen(
                    book = book,
                    onBack = { navController.popBackStack() }
                )
            }
        }

        // Pantalla de lista de personajes
        composable(route = Destinations.CharacterRoute.route) {
            CharacterScreen(
                viewModel = mainViewModel,
                onCharacterClick = { characterIndex ->
                    if (characterIndex != -1) {
                        navController.navigate(Destinations.CharacterDetailsRoute.createRoute(characterIndex))
                    }
                },
                onRandomCharacterClick = { character ->
                    navController.navigate(Destinations.CharacterDetailsRoute.createRoute(character.index))
                },
                onBack = { navController.popBackStack() }
            )
        }

        // Pantalla de detalles de un personaje
        composable(
            route = Destinations.CharacterDetailsRoute.route,
            arguments = listOf(navArgument("characterIndex") { defaultValue = -1 })
        ) { backStackEntry ->
            val characterIndex = backStackEntry.arguments?.getInt("characterIndex") ?: -1
            val character = mainViewModel.getCharacterByIndex(characterIndex)

            if (character != null) {
                CharacterDetailsScreen(
                    character = character,
                    onBack = { navController.popBackStack() }
                )
            }
        }

        // NUEVA: Pantalla de lista de casas
        composable(route = Destinations.HouseRoute.route) {
            HousesScreen(
                viewModel = mainViewModel,
                onHouseClick = { houseIndex ->
                    if (houseIndex != -1) {
                        navController.navigate(Destinations.HouseDetailsRoute.createRoute(houseIndex))
                    }
                },
                onRandomHouseClick = { house ->
                    navController.navigate(Destinations.HouseDetailsRoute.createRoute(house.index))
                },
                onBack = { navController.popBackStack() }
            )
        }

        // NUEVA: Pantalla de detalles de una casa
        composable(
            route = Destinations.HouseDetailsRoute.route,
            arguments = listOf(navArgument("houseIndex") { defaultValue = -1 })
        ) { backStackEntry ->
            val houseIndex = backStackEntry.arguments?.getInt("houseIndex") ?: -1
            val house = mainViewModel.getHouseByIndex(houseIndex)

            if (house != null) {
                HouseDetailsScreen(
                    house = house,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
