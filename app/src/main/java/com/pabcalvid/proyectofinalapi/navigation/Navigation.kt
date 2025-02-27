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
                onBooks = { navController.navigate(Destinations.BookRoute.route) },
                onCharacters = { navController.navigate(Destinations.CharacterRoute.route) },
                onHouses = { navController.navigate(Destinations.HouseRoute.route) }
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
                }
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
                    viewModel = mainViewModel
                )
            }
        }

        // Pantalla de lista de personajes
        composable(route = Destinations.CharacterRoute.route) {
            CharacterScreen(
                viewModel = mainViewModel,
                onCharacterClick = { characterNickname ->
                    if (characterNickname.isNotEmpty()) {
                        navController.navigate(Destinations.CharacterDetailsRoute.createRoute(characterNickname))
                    }
                },
                onRandomCharacterClick = { character ->
                    navController.navigate(Destinations.CharacterDetailsRoute.createRoute(character.nickname))
                },
            )
        }

        // Pantalla de detalles de un personaje
        composable(
            route = Destinations.CharacterDetailsRoute.route,
            arguments = listOf(navArgument("nickname") { defaultValue = "" })
        ) { backStackEntry ->
            val characterNickname = backStackEntry.arguments?.getString("nickname") ?: ""
            val character = mainViewModel.getCharacterByNickname(characterNickname)

            if (character != null) {
                CharacterDetailsScreen(
                    viewModel = mainViewModel
                )
            }
        }

        composable(route = Destinations.HouseRoute.route) {
            HousesScreen(
                viewModel = mainViewModel,
                onHouseClick = { house ->
                    if (house.isNotEmpty()) {
                        navController.navigate(Destinations.HouseDetailsRoute.createRoute(house))
                    }
                },
                onRandomHouseClick = { house ->
                    navController.navigate(Destinations.HouseDetailsRoute.createRoute(house.house))
                },
            )
        }

        composable(
            route = Destinations.HouseDetailsRoute.route,
            arguments = listOf(navArgument("house") { defaultValue = "" })
        ) { backStackEntry ->
            val nameHouse = backStackEntry.arguments?.getString("house") ?: ""
            val house = mainViewModel.getHouseByHouse(nameHouse)

            if (house != null) {
                HouseDetailsScreen(
                    viewModel = mainViewModel
                )
            }
        }

        composable(route = Destinations.FavoritesRoute.route) {
            FavoritesBooksScreen(
                viewModel = mainViewModel
            )
        }

        composable(route = Destinations.FavoritesCharactersRoute.route) {
            FavoritesCharactersScreen(
                viewModel = mainViewModel
            )
        }

        composable(route = Destinations.FavoritesHousesRoute.route) {
            FavoriteHousesScreen(
                viewModel = mainViewModel
            )
        }

        composable(route = Destinations.FavoritesSelectionRoute.route) {
            FavoritesSelectionScreen(
                onBooks = { navController.navigate(Destinations.FavoritesRoute.route) },
                onCharacters = { navController.navigate(Destinations.FavoritesCharactersRoute.route) },
                onHouses = { navController.navigate(Destinations.FavoritesHousesRoute.route) }
            )
        }

        composable(route = Destinations.AuthRoute.route) {
            AuthorScreen()
        }
    }
}
